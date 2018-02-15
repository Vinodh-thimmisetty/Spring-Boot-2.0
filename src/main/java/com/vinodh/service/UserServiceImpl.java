package com.vinodh.service;

import com.vinodh.domain.RoleRepository;
import com.vinodh.domain.User;
import com.vinodh.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public User findUserByName(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public void saveUser(User user) {

        userRepository.save(user);
    }
}
