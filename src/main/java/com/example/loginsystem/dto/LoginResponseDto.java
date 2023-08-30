package com.example.loginsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class LoginResponseDto {
    private String jwt;
}
