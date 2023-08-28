package com.example.loginsystem;

import com.example.loginsystem.repository.RoleRepository;
import com.example.loginsystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginSystemApplication.class, args);
    }


}
