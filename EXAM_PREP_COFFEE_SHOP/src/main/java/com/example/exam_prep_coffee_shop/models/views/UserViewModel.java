package com.example.exam_prep_coffee_shop.models.views;

public class UserViewModel {

    private String username;
    private Integer ordersCount;

    public UserViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getOrdersCount() {
        return ordersCount;
    }

    public UserViewModel setOrdersCount(Integer ordersCount) {
        this.ordersCount = ordersCount;
        return this;
    }
}
