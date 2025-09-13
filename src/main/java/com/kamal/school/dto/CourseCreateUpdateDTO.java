//package com.kamal.school.dto;
//
//import jakarta.validation.constraints.NotBlank;
//import lombok.Data;
//
//@Data
//public class CourseCreateUpdateDTO {
//    @NotBlank
//    private String code;
//    @NotBlank
//    private String title;
//    private String description;
//}
//


package com.kamal.school.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateUpdateDTO {

    @NotBlank(message = "Course name is required")
    @Size(max = 100, message = "Course name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Course code is required")
    @Size(max = 20, message = "Course code must be less than 20 characters")
    private String code;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    private Integer creditHours;

    private Boolean isActive = true;
}