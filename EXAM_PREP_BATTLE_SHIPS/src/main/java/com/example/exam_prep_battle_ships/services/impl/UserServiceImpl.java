package com.example.exam_prep_battle_ships.services.impl;

import com.example.exam_prep_battle_ships.models.binding.UserLoginBindingModel;
import com.example.exam_prep_battle_ships.models.binding.UserRegisterBindingModel;
import com.example.exam_prep_battle_ships.models.entities.User;
import com.example.exam_prep_battle_ships.models.services.UserServiceModel;
import com.example.exam_prep_battle_ships.repositories.UserRepository;
import com.example.exam_prep_battle_ships.services.UserService;
import com.example.exam_prep_battle_ships.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public boolean registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        try {
            UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
            this.userRepository.save(this.modelMapper.map(userServiceModel, User.class));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(UserLoginBindingModel userLoginBindingModel) {
        return this.userRepository
                .findByUsernameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword())
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginCurrentUser(UserServiceModel userServiceModel) {
        this.currentUser.setId(userServiceModel.getId());
    }

    @Override
    public void logoutCurrentUser() {
        this.currentUser.setId(null);
    }

    @Override
    public User findUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}
