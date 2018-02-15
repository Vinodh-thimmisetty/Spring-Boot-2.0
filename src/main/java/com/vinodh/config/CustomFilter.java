package com.vinodh.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

@Component
@Slf4j
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Custom Filter is Initialized ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Filtering Starts..");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpServletRequest.getSession(true);
        SecurityContextImpl securityContext = (SecurityContextImpl) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");

        if(Objects.nonNull(securityContext)){
            UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            log.info("Logged in UserName  :: {}", username);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            authorities.stream()
                    .forEach(x->log.info("Roles allowed for this Request inside the Filter is :: {}",x));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("Filter is Destroyed !!");
    }
}
