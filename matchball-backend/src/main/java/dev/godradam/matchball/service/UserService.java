package dev.godradam.matchball.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.godradam.matchball.model.ApplicationUser;
import dev.godradam.matchball.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<ApplicationUser> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public Optional<ApplicationUser> login(ApplicationUser userToLogin) {
        return getUserByUsername(userToLogin.getUsername())
                .filter(userFromRepo -> passwordEncoder.matches(userToLogin.getPassword(), userFromRepo.getPassword()));
    }

    public Optional<ApplicationUser> register(ApplicationUser userToRegister) {
        return getUserByUsername(userToRegister.getUsername()).isPresent() 
                ? Optional.empty()
                : Optional.of(userRepo.save(userToRegister.toBuilder()
                        .password(passwordEncoder.encode(userToRegister.getPassword())).build()));
    }

}
