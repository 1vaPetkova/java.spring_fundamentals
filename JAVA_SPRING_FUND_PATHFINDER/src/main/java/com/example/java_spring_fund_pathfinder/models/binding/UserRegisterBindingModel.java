package com.example.java_spring_fund_pathfinder.models.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    private String username;
    private String fullName;
    private String email;
    private Integer age;
    private String password;
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    @Size(min = 4, max = 20)
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegisterBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @Min(10)
    public Integer getAge() {
        return age;
    }

    public UserRegisterBindingModel setAge(Integer age) {
        this.age = age;
        return this;
    }
    @Size(min = 4, max = 20)
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
