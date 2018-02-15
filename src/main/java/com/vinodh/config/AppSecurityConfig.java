package com.vinodh.config;

import com.vinodh.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomFilter customFilter;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      /*  auth.inMemoryAuthentication()
                .withUser("vinodh").password("{noop}abcd1234").roles("USER")
             .and()
                .withUser("pavan").password("{noop}abcd1234").roles("ADMIN");*/

      auth.userDetailsService(customUserDetailsService)
            .passwordEncoder(getPasswordEncoder());

    }

    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return true;
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/vinodh/home")
                    .hasRole("USER")
                .antMatchers("**/vinodh/*")
                    .hasAnyRole("USER", "ADMIN")
                .anyRequest()
                    .fullyAuthenticated()
            .and()
                .formLogin().permitAll()
            .and()
                .addFilterBefore(customFilter, BasicAuthenticationFilter.class)
            .httpBasic()
            .and()
            .csrf()
                .disable()
            .cors()
                .disable();
    }


}



