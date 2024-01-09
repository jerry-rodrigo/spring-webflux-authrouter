package com.springboot.webflux.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserDto {
    private String username;
    private String email;
    private String password;
    private List<String> roles;
}
