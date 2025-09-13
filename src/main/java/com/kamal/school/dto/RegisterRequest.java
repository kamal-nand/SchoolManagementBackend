package com.kamal.school.dto;

import com.kamal.school.domain.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private Role role = Role.STUDENT;
}
