package com.kamal.school.service;

import com.kamal.school.domain.Student;
import com.kamal.school.dto.StudentCreateUpdateDTO;
import com.kamal.school.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student create(StudentCreateUpdateDTO dto) {
        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        var s = Student.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
        return studentRepository.save(s);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student update(Long id, StudentCreateUpdateDTO dto) {
        var s = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        s.setFirstName(dto.getFirstName());
        s.setLastName(dto.getLastName());
        s.setEmail(dto.getEmail());
        return studentRepository.save(s);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
