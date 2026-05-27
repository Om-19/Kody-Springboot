package com.olp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olp.service.EnrollmentServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentServiceImpl enrollmentServiceImpl;

    @PostMapping("/enroll/{courseId}")
    public String postMethodName(@PathVariable Long courseId, Authentication authentication) {
        return enrollmentServiceImpl.enrollCourse(courseId, authentication);
    }

}
