package com.olp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olp.dto.request.CourseDto;
import com.olp.dto.response.GenericResponse;
import com.olp.service.CourseServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseServiceImpl courseServiceImpl;

    /*
     * If instructor id is not given, it will take it through SecurityContext
     */
    @PostMapping("/instructor/create")
    public ResponseEntity<GenericResponse<?>> createCourse(@Valid @RequestBody CourseDto entity) {
        return ResponseEntity.ok(
                GenericResponse.builder()
                        .statusCode(200)
                        .message("Courses fetched successfully")
                        .data(courseServiceImpl.saveCourse(entity))
                        .build());
    }

    @GetMapping
    public ResponseEntity<GenericResponse<?>> getAllCourses() {

        return ResponseEntity.ok(
                GenericResponse.builder()
                        .statusCode(200)
                        .message("Courses fetched successfully")
                        .data(courseServiceImpl.getAllCourses())
                        .build());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<GenericResponse<?>> getCourseById(
            @PathVariable Long courseId) {

        return ResponseEntity.ok(
                GenericResponse.builder()
                        .statusCode(200)
                        .message("Course fetched successfully")
                        .data(courseServiceImpl.getCourseById(courseId))
                        .build());
    }

    @PutMapping("/instructor/update/{courseId}")
    public ResponseEntity<GenericResponse<?>> updateCourse(
            @PathVariable Long courseId,
            @Valid @RequestBody CourseDto dto) {

        return ResponseEntity.ok(
                GenericResponse.builder()
                        .statusCode(200)
                        .message("Course updated successfully")
                        .data(courseServiceImpl.updateCourse(courseId, dto))
                        .build());
    }

}
