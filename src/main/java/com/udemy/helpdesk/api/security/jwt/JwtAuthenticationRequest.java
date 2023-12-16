package com.udemy.helpdesk.api.security.jwt;

import org.springframework.stereotype.Component;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    private String password;

    public JwtAuthenticationRequest(){
        super();
    }

    public JwtAuthenticationRequest(String email, String password){
        super();
        this.email=email;
        this.password = password;
    }

}
