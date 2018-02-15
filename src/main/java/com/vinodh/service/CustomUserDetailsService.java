package com.vinodh.service;

import com.vinodh.domain.CustomUserDetails;
import com.vinodh.domain.User;
import com.vinodh.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("customUserDetailsService")
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = Optional.of(userRepository.findByUserName(userName));
        log.info("user",user.get().getEmail());
        user.orElseThrow(() -> new UsernameNotFoundException("UserName"+ userName +" not Found"));
        return user.map(eachUser -> new CustomUserDetails(eachUser)).get();
    }
}
