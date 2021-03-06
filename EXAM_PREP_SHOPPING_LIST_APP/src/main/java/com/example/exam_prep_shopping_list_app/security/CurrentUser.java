package com.example.exam_prep_shopping_list_app.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Long id;
    private String username;

    public CurrentUser() {
    }

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }


    public boolean isLoggedIn(){
        return this.id!=null;
    }

    public void setLoggedIn(boolean isLoggedIn){
        this.id = null;
    }
}
