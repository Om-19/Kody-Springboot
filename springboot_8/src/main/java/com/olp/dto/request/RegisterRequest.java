package com.olp.dto.request;

import com.olp.entity.enums.Role;

import lombok.Data;

@Data

public class RegisterRequest {

    private String name;

    private String email;

    private String password;

    private Role role;
}