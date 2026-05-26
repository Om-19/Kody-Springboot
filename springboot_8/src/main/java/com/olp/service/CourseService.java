package com.olp.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.olp.dto.request.CreateCourseRequest;
import com.olp.dto.response.CourseResponse;
import com.olp.entity.Institution;
import com.olp.entity.Instructor;
import com.olp.exception.customExc.UserNotFoundException;
import com.olp.repository.CoursesRepository;
import com.olp.repository.InstitutionRepository;
import com.olp.repository.InstructorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CoursesRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final InstitutionRepository institutionRepository;

    /*
     * Create Course
     */
    public CourseResponse createCourse(CreateCourseRequest requestDto) {
        // Get Logged in Instructor email
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        // Find Instructor from DB
        Instructor instructor = instructorRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Instructor Not Found."));

        Institution institution = null;

        if (requestDto.getInstitutionId() != null) {
            institution = institutionRepository
                    .findById(requestDto.getInstitutionId())

                    .orElseThrow(() -> new RuntimeException("Institution not found"));
        }

    }
}
