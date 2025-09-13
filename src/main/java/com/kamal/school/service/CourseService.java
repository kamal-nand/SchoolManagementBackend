package com.kamal.school.service;
//
//import com.kamal.school.domain.Course;
//import com.kamal.school.dto.CourseCreateUpdateDTO;
//import com.kamal.school.repo.CourseRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class CourseService {
//    private final CourseRepository courseRepository;
//
//    public Course create(CourseCreateUpdateDTO dto) {
//        if (courseRepository.existsByCode(dto.getCode())) {
//            throw new IllegalArgumentException("Course code already exists");
//        }
//        var c = Course.builder()
//                .code(dto.getCode())
//                .title(dto.getTitle())
//                .description(dto.getDescription())
//                .build();
//        return courseRepository.save(c);
//    }
//
//    public List<Course> findAll() { return courseRepository.findAll(); }
//
//    public Course update(Long id, CourseCreateUpdateDTO dto) {
//        var c = courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Course not found"));
//        c.setCode(dto.getCode());
//        c.setTitle(dto.getTitle());
//        c.setDescription(dto.getDescription());
//        return courseRepository.save(c);
//    }
//
////    public void delete(Long id) { courseRepository.deleteById(id); }
//
//    public void delete(Long id) {
//        if (!courseRepository.existsById(id)) {
//            throw new RuntimeException("Course not found with id " + id);
//        }
//        courseRepository.deleteById(id);
//    }
//
//}



//package com.kamal.school.service;

import com.kamal.school.domain.Course;
import com.kamal.school.dto.CourseCreateUpdateDTO;
//import com.kamal.school.repository.CourseRepository;
import com.kamal.school.repo.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    // CREATE - Add a new course
    @Transactional
    public Course create(CourseCreateUpdateDTO dto) {
        // Check if course code already exists
        if (courseRepository.existsByCode(dto.getCode())) {
            throw new RuntimeException("Course code already exists: " + dto.getCode());
        }

        Course course = new Course();
        mapDtoToEntity(dto, course);

        return courseRepository.save(course);
    }

    // READ - Get all courses
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    // READ - Get all active courses
    public List<Course> findAllActive() {
        return courseRepository.findByIsActive(true);
    }

    // READ - Get course by ID
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    // READ - Get course by code
    public Course findByCode(String code) {
        return courseRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Course not found with code: " + code));
    }

    // UPDATE - Update an existing course
    @Transactional
    public Course update(Long id, CourseCreateUpdateDTO dto) {
        Course course = findById(id);

        // Check if the new code is already taken by another course
        if (!course.getCode().equals(dto.getCode()) &&
                courseRepository.existsByCodeAndIdNot(dto.getCode(), id)) {
            throw new RuntimeException("Course code already exists: " + dto.getCode());
        }

        mapDtoToEntity(dto, course);

        return courseRepository.save(course);
    }

    // DELETE - Delete a course (soft delete by setting isActive to false)
    @Transactional
    public void delete(Long id) {
        Course course = findById(id);
        course.setIsActive(false);
        courseRepository.save(course);
    }

    // HARD DELETE - Permanently delete a course from database
    @Transactional
    public void hardDelete(Long id) {
        Course course = findById(id);
        courseRepository.delete(course);
    }

    // SEARCH - Search courses by keyword in name or code
    public List<Course> searchCourses(String keyword) {
        return courseRepository.searchCourses(keyword);
    }

    // Helper method to map DTO to Entity
    private void mapDtoToEntity(CourseCreateUpdateDTO dto, Course course) {
        course.setName(dto.getName());
        course.setCode(dto.getCode());
        course.setDescription(dto.getDescription());
        course.setCreditHours(dto.getCreditHours());

        if (dto.getIsActive() != null) {
            course.setIsActive(dto.getIsActive());
        }
    }
}