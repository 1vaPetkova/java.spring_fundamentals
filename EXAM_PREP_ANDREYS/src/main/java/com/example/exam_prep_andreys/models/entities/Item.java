package com.example.exam_prep_andreys.models.entities;

import com.example.exam_prep_andreys.models.entities.enums.GenderEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item extends BaseEntity{

    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private GenderEnum gender;

    public Item() {
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Item setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @ManyToOne
    public Category getCategory() {
        return category;
    }

    public Item setCategory(Category category) {
        this.category = category;
        return this;
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public GenderEnum getGender() {
        return gender;
    }

    public Item setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }
}
