package com.example.exam_prep_shopping_list_app.services.impl;

import com.example.exam_prep_shopping_list_app.models.binding.UserRegisterBindingModel;
import com.example.exam_prep_shopping_list_app.models.entities.User;
import com.example.exam_prep_shopping_list_app.models.services.UserServiceModel;
import com.example.exam_prep_shopping_list_app.repositories.UserRepository;
import com.example.exam_prep_shopping_list_app.security.CurrentUser;
import com.example.exam_prep_shopping_list_app.services.UserService;
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
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        this.userRepository.save( this.modelMapper.map(userServiceModel, User.class));
    }
}
