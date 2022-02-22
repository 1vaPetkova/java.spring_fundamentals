package com.example.java_spring_fund_lab_01.services.impl;

import com.example.java_spring_fund_lab_01.models.entities.UserEntity;
import com.example.java_spring_fund_lab_01.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MobileleleUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public MobileleleUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //The purpose of this method is to map our user representation (UserEntity)
        //to the user representation in the spring security (UserDetails).
        //The only thing that spring will provide to use is the username.
        //The username will come from the html login form
        UserEntity user = this.userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("User with name %s not found!", username)));
        return mapToUserDetails(user);
    }

    private static UserDetails mapToUserDetails(UserEntity userEntity) {

        // GrantedAuthority is the representation of a user role in the spring world.
        // SimpleGrantedAuthority is an implementation of GrantedAuthority which spring provides for our convenience.
        // Our representation of role is UserRoleEntity

        List<GrantedAuthority> authorities = userEntity.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                .collect(Collectors.toList());

        // User is the spring implementation of UserDetails interface
        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}
