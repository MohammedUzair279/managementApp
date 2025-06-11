package com.jktech.managementApp.mangementApplication.dto;

import com.jktech.managementApp.mangementApplication.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Set<Role> roles;
}
