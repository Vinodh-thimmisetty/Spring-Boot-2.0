package com.vinodh.service;

import com.vinodh.domain.User;

public interface UserService {
    User findUserByName(String email);
    void saveUser(User user);
}
