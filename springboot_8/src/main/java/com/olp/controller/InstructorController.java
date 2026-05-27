package com.olp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olp.dto.response.GenericResponse;
import com.olp.service.InstructorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
@Slf4j
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/test")
    public String instructor() {
        return "Instructor Api";
    }

    @GetMapping("/getAllstudents/{courseId}")
    public ResponseEntity<GenericResponse<?>> getMethodName(@PathVariable Long courseId,
            Authentication authentication) {

        log.info("Get All Students with Course.");

        return ResponseEntity.ok(
                GenericResponse.builder()
                        .statusCode(200)
                        .message("Student Enrolled in Current Course")
                        .data(instructorService.getAllStudentForCourse(courseId, authentication))
                        .build());
    }

}
