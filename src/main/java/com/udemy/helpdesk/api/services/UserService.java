package com.udemy.helpdesk.api.services;

import com.udemy.helpdesk.api.entities.User;
import org.springframework.data.domain.Page;

public interface UserService {

    User findById(String id);

    User findByEmail(String email);

    Page<User> findAll(int page, int count);

    User createOrUpdate(User user);

    void delete(String id);

}