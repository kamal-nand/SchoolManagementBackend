package com.kamal.school.service;

import com.kamal.school.domain.Course;
import com.kamal.school.domain.Enrollment;
import com.kamal.school.domain.Student;
import com.kamal.school.dto.EnrollmentDtos;
import com.kamal.school.repo.CourseRepository;
import com.kamal.school.repo.EnrollmentRepository;
import com.kamal.school.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public Enrollment enroll(EnrollmentDtos dto) {
        Student s = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Course c = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        var e = Enrollment.builder()
                .student(s)
                .course(c)
                .enrolledAt(Instant.now())
                .build();
        return enrollmentRepository.save(e);
    }

    public List<Enrollment> list() { return enrollmentRepository.findAll(); }
}
