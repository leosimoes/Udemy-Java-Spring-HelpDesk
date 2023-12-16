package com.udemy.helpdesk.api.security.models;

import com.udemy.helpdesk.api.entities.User;

public class CurrentUser {

    private String token;
    private User user;

    public CurrentUser(String token, User user){
        this.token = token;
        this.user = user;
    }
}