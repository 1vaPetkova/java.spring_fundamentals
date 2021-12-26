package com.example.java_spring_fund_lab_01.repositories;

import com.example.java_spring_fund_lab_01.models.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Long> {
}