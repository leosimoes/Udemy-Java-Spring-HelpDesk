package com.udemy.helpdesk.api.security.jwt;

import com.udemy.helpdesk.api.entities.User;
import com.udemy.helpdesk.api.enums.ProfileEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwrUserFactory {

    private JwrUserFactory(){
    }

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getProfile())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(ProfileEnum profileEnum){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(profileEnum.toString()));
        return authorities;
    }

}
