package com.example.java_spring_fund_pathfinder.models.service;

import com.example.java_spring_fund_pathfinder.models.entities.Role;
import com.example.java_spring_fund_pathfinder.models.entities.enums.Level;

import java.util.HashSet;
import java.util.Set;

public class UserServiceModel {

    private Long id;
    private Integer age;
    private String fullName;
    private Level level;
    private String password;
    private String username;
    private Set<Role> roles;

    public UserServiceModel() {
        this.roles = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserServiceModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public UserServiceModel setLevel(Level level) {
        this.level = level;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public UserServiceModel setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }
}
