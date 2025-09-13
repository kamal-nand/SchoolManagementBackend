package com.kamal.school.service;

import com.kamal.school.domain.Student;
import com.kamal.school.dto.StudentCreateUpdateDTO;
import com.kamal.school.repo.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import(StudentService.class)
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void createAndListStudents() {
        StudentCreateUpdateDTO dto = new StudentCreateUpdateDTO();
        dto.setFirstName("Alice");
        dto.setLastName("Lee");
        dto.setEmail("alice@example.com");
        Student saved = studentService.create(dto);
        assertThat(saved.getId()).isNotNull();
        assertThat(studentService.findAll()).hasSize(1);
    }

    @Test
    void duplicateEmailShouldFail() {
        StudentCreateUpdateDTO dto = new StudentCreateUpdateDTO();
        dto.setFirstName("Bob");
        dto.setLastName("Kim");
        dto.setEmail("dup@example.com");
        studentService.create(dto);
        assertThrows(IllegalArgumentException.class, () -> studentService.create(dto));
    }
}
