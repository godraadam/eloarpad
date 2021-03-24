package dev.godradam.matchball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import dev.godradam.matchball.model.ApplicationUser;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = userService.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No such user!"));
        return User.builder()
        .username(user.getUsername())
        .password(user.getPassword())
        .build();
    }
    
}
