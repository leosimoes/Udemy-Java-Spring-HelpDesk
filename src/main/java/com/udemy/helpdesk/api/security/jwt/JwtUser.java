package com.udemy.helpdesk.api.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

    private static final long serialVersionUID = -7471177486146141709L;
    private final String id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> autorities;

    public JwtUser(String id, String username, String password, Collection<? extends GrantedAuthority> autorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.autorities = autorities;
    }

    @JsonIgnore
    public String getId(){
        return this.id;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.autorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
