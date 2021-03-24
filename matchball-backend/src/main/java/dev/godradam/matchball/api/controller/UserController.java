package dev.godradam.matchball.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.godradam.matchball.api.dto.CredentialsDTO;
import dev.godradam.matchball.api.dto.UserDTO;
import dev.godradam.matchball.model.ApplicationUser;
import dev.godradam.matchball.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/api/user/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        return modelMapper.map(userService.getUserByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), UserDTO.class);
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody CredentialsDTO credentials) {
        return modelMapper.map(userService.login(modelMapper.map(credentials, ApplicationUser.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED)), UserDTO.class);
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody CredentialsDTO credentials) {
        return modelMapper.map(userService.register(modelMapper.map(credentials, ApplicationUser.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT)), UserDTO.class);
    }
}
