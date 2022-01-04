package com.example.java_spring_fund_pathfinder.services.impl;

import com.example.java_spring_fund_pathfinder.models.entities.User;
import com.example.java_spring_fund_pathfinder.models.entities.enums.Level;
import com.example.java_spring_fund_pathfinder.models.entities.enums.RoleType;
import com.example.java_spring_fund_pathfinder.models.service.UserServiceModel;
import com.example.java_spring_fund_pathfinder.repositories.UserRepository;
import com.example.java_spring_fund_pathfinder.services.RoleService;
import com.example.java_spring_fund_pathfinder.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;



    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

    }


    @Override
    public boolean isAuthenticated(String username, String password) {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword()) ||
                user.getPassword().equals(password);
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class)
                .setLevel(Level.BEGINNER)
                .setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        if (isAuthenticated(username, password)) {
            User user = this.userRepository.findByUsername(username);
            return this.modelMapper.map(user, UserServiceModel.class);
        }
        return null;
    }
}
