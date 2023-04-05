package com.internetx.security.service;

import com.internetx.domain.User;
import com.internetx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 2. CLASS TO IMPLEMENT
 * Security helper class
 * this class is created to find the user from DB.
 * The user which has been found will be sent to com.internetx.security.service.UserDetailsImpl class build method.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        return UserDetailsImpl.build(user);
    }
}
