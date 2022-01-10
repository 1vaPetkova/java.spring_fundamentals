package com.example.exam_prep_coffee_shop.models.service;

import com.example.exam_prep_coffee_shop.models.entities.Category;
import com.example.exam_prep_coffee_shop.models.entities.User;
import com.example.exam_prep_coffee_shop.models.entities.enums.CategoryNameEnum;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public class OrderServiceModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDateTime orderTime;
    private CategoryNameEnum category;
    private String description;
    private User employee;

    public OrderServiceModel() {
    }


    public Long getId() {
        return id;
    }

    public OrderServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderServiceModel setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public OrderServiceModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getEmployee() {
        return employee;
    }

    public OrderServiceModel setEmployee(User employee) {
        this.employee = employee;
        return this;
    }
}
