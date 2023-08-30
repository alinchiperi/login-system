package com.example.loginsystem.controller;

import com.example.loginsystem.dto.LoginResponseDto;
import com.example.loginsystem.dto.RegisterUserDto;
import com.example.loginsystem.model.User;
import com.example.loginsystem.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto userDto) {
        return ResponseEntity.ok().body(authService.registerUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody RegisterUserDto userDto){
        return ResponseEntity.ok().body(authService.login(userDto));
    }
}
