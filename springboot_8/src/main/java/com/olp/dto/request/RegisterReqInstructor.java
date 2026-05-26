package com.olp.dto.request;

import com.olp.entity.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterReqInstructor {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // @Enumerated(EnumType.STRING)
    // private Category category;

    // private Long institutionId;
}
