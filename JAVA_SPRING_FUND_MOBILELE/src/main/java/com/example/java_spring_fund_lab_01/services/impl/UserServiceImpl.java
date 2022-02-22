package com.example.java_spring_fund_lab_01.services.impl;

import com.example.java_spring_fund_lab_01.models.entities.UserEntity;
import com.example.java_spring_fund_lab_01.models.entities.UserRoleEntity;
import com.example.java_spring_fund_lab_01.models.entities.enums.UserRoleEnum;
import com.example.java_spring_fund_lab_01.models.service.UserRegistrationServiceModel;
import com.example.java_spring_fund_lab_01.repositories.UserRepository;
import com.example.java_spring_fund_lab_01.repositories.UserRolesRepository;
import com.example.java_spring_fund_lab_01.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;


    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                           UserRolesRepository userRolesRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = this.userRolesRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = this.userRolesRepository.findByRole(UserRoleEnum.USER);

            UserEntity admin = new UserEntity();
            admin
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setActive(true);

            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);

            UserEntity pesho = new UserEntity();
            pesho
                    .setUsername("pesho")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Pesho")
                    .setLastName("Petrov")
                    .setActive(true);

            pesho.setRoles(Set.of(userRole));
            userRepository.save(pesho);
        }
    }

    private void initializeRoles() {

        if (this.userRolesRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);

            this.userRolesRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {

        UserRoleEntity userRole = this.userRolesRepository.findByRole(UserRoleEnum.USER);

        UserEntity newUser = new UserEntity();

        newUser.
                setUsername(userRegistrationServiceModel.getUsername()).
                setFirstName(userRegistrationServiceModel.getFirstName()).
                setLastName(userRegistrationServiceModel.getLastName()).
                setActive(true).
                setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword())).
                setRoles(Set.of(userRole));

        newUser = userRepository.save(newUser);

        // this is the Spring representation of a user
//        UserDetails principal = mobileleUserService.loadUserByUsername(newUser.getUsername());
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                principal,
//                newUser.getPassword(),
//                principal.getAuthorities()
//        );
//
//        SecurityContextHolder.
//                getContext().
//                setAuthentication(authentication);
    }

    public boolean isUserNameFree(String username) {
        return this.userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

}
