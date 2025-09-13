package com.kamal.school.dto;

import lombok.Data;

@Data
public class CourseResponse {
    private Long id;
    private String code;
    private String title;
    private String description;
}
