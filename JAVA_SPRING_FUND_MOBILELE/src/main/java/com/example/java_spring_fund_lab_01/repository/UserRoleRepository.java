package com.example.java_spring_fund_lab_01.repository;


import com.example.java_spring_fund_lab_01.model.entity.UserRoleEntity;
import com.example.java_spring_fund_lab_01.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

  UserRoleEntity findByRole(UserRoleEnum role);

}
