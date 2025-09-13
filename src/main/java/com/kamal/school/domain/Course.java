//package com.kamal.school.domain;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import lombok.*;
//
//@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
//public class Course {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank
//    @Column(unique = true, nullable = false)
//    private String code;
//
//    @NotBlank
//    private String title;
//
//    private String description;
//}


package com.kamal.school.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course name is required")
    @Size(max = 100, message = "Course name must be less than 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Course code is required")
    @Size(max = 20, message = "Course code must be less than 20 characters")
    @Column(nullable = false, unique = true, length = 20)
    private String code;

    @Size(max = 500, message = "Description must be less than 500 characters")
    @Column(length = 500)
    private String description;

    @Column(name = "credit_hours")
    private Integer creditHours;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}