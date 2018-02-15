package com.vinodh.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/vinodh")
@Slf4j
public class StudentController {

    @Value("${welcome-msg}")
    private String welcomeMsg;

    @GetMapping("/home")
    @PreAuthorize("hasAnyRole('VINODH','USER')")
    public String hello(@AuthenticationPrincipal final UserDetails userDetails) {
        String username = userDetails.getUsername();
        log.info("Logged in UserName is :: {}", username);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        authorities.stream()
                    .forEach(x->log.info("Roles allowed for this Request is :: {}",x));
        return welcomeMsg;
    }

}
