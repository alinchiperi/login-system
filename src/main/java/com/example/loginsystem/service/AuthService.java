package com.example.loginsystem.service;

import com.example.loginsystem.dto.LoginResponseDto;
import com.example.loginsystem.dto.RegisterUserDto;
import com.example.loginsystem.model.Role;
import com.example.loginsystem.model.User;
import com.example.loginsystem.repository.RoleRepository;
import com.example.loginsystem.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public User registerUser(RegisterUserDto userDto) {
        String password = passwordEncoder.encode(userDto.getPassword());
        Optional<Role> role = roleRepository.findByAuthority("USER");
        Set<Role> authorities = new HashSet<>();
        role.ifPresent(authorities::add);
        return userRepository.save(new User(userDto.getUsername(), password, authorities));
    }

    public LoginResponseDto login(RegisterUserDto userDto) {
        try {

            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDto(token);
        } catch (AuthenticationException e) {
            return new LoginResponseDto("");
        }
    }
}
