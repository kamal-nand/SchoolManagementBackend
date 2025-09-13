//package com.kamal.school.web;
//
//import com.kamal.school.domain.Course;
//import com.kamal.school.dto.CourseCreateUpdateDTO;
//import com.kamal.school.service.CourseService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping("/api/courses")
//@RequiredArgsConstructor
//public class CourseController {
//    private final CourseService courseService;
//
//    @PostMapping
//    public ResponseEntity<Course> create(@Valid @RequestBody CourseCreateUpdateDTO dto) {
//        return ResponseEntity.ok(courseService.create(dto));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Course>> list() {
//        return ResponseEntity.ok(courseService.findAll());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Course> update(@PathVariable Long id, @Valid @RequestBody CourseCreateUpdateDTO dto) {
//        return ResponseEntity.ok(courseService.update(id, dto));
//    }
//
////    @DeleteMapping("/{id}")
////    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
////        courseService.delete(id);
////        return ResponseEntity.noContent().build();
////    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
//        courseService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//}



package com.kamal.school.web;

import com.kamal.school.domain.Course;
import com.kamal.school.dto.CourseCreateUpdateDTO;
import com.kamal.school.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    // CREATE - POST: Create a new course
    @PostMapping
    public ResponseEntity<Course> create(@Valid @RequestBody CourseCreateUpdateDTO dto) {
        try {
            Course createdCourse = courseService.create(dto);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // READ - GET: Get all courses
    @GetMapping
    public ResponseEntity<List<Course>> list() {
        try {
            List<Course> courses = courseService.findAll();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - GET: Get all active courses
    @GetMapping("/active")
    public ResponseEntity<List<Course>> listActive() {
        try {
            List<Course> courses = courseService.findAllActive();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - GET: Get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        try {
            Course course = courseService.findById(id);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // READ - GET: Get course by code
    @GetMapping("/code/{code}")
    public ResponseEntity<Course> getByCode(@PathVariable String code) {
        try {
            Course course = courseService.findByCode(code);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE - PUT: Update an existing course
    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @Valid @RequestBody CourseCreateUpdateDTO dto) {
        try {
            Course updatedCourse = courseService.update(id, dto);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE - DELETE: Soft delete a course (set isActive to false)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            courseService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - DELETE: Permanently delete a course
    @DeleteMapping("/hard/{id}")
    public ResponseEntity<Void> hardDelete(@PathVariable("id") Long id) {
        try {
            courseService.hardDelete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // SEARCH - GET: Search courses by keyword
    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam String keyword) {
        try {
            List<Course> courses = courseService.searchCourses(keyword);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}