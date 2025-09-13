package com.kamal.school.web;

import com.kamal.school.domain.Student;
import com.kamal.school.dto.StudentCreateUpdateDTO;
import com.kamal.school.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody StudentCreateUpdateDTO dto) {
        return ResponseEntity.ok(studentService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<Student>> list() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @Valid @RequestBody StudentCreateUpdateDTO dto) {
        return ResponseEntity.ok(studentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
