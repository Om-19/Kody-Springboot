package com.olp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olp.dto.response.GenericResponse;
import com.olp.entity.enums.EnrollmentStatus;
import com.olp.service.EnrollmentServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentServiceImpl enrollmentServiceImpl;

    /*
     * Assign course to student
     */
    @PostMapping("/enroll/{courseId}")
    public String postMethodName(
            @PathVariable Long courseId,
            Authentication authentication) {
        return enrollmentServiceImpl.enrollCourse(courseId, authentication);
    }

    /*
     * Update Course status
     */
    @PatchMapping("/enroll/{courseId}/status")
    public ResponseEntity<GenericResponse<?>> updateStatus(
            @PathVariable Long courseId,
            @RequestParam EnrollmentStatus status,
            Authentication authentication) {

        return ResponseEntity.ok(
                GenericResponse.builder()
                        .statusCode(200)
                        .message("Course Status Updated Successfully.")
                        .data(enrollmentServiceImpl
                                .updateStatus(
                                        courseId,
                                        authentication,
                                        status))
                        .build());
    }
}
