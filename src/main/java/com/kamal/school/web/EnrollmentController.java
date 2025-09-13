package com.kamal.school.web;

import com.kamal.school.domain.Enrollment;
import com.kamal.school.dto.EnrollmentDtos;
import com.kamal.school.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<Enrollment> enroll(@Valid @RequestBody EnrollmentDtos dto) {
        return ResponseEntity.ok(enrollmentService.enroll(dto));
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>> list() {
        return ResponseEntity.ok(enrollmentService.list());
    }
}
