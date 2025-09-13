package com.kamal.school.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentDtos {
    @NotNull
    private Long studentId;
    @NotNull
    private Long courseId;
}
