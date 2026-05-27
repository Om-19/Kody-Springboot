package com.olp.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.olp.dto.request.StudentDto;
import com.olp.entity.Course;
import com.olp.entity.Instructor;
import com.olp.entity.Student;
import com.olp.repository.CoursesRepository;
import com.olp.repository.EnrollmentRepository;
import com.olp.repository.InstructorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CoursesRepository coursesRepository;

    public List<StudentDto> getAllStudentForCourse(Long courseId, Authentication authentication) {

        // Logged in instructor
        String email = authentication.getName();

        Instructor instructor = instructorRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        // Fetch course
        Course course = coursesRepository
                .findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Ownership check
        if (!course.getInstructor().getId()
                .equals(instructor.getId())) {

            throw new RuntimeException(
                    "You are not authorized to view students of this course");
        }

        // Fetch enrollments
        List<Student> students = enrollmentRepository
                .getAllStudentForCourse(courseId);

        return students.stream()
                .map(student -> {
                    StudentDto dto = new StudentDto();
                    BeanUtils.copyProperties(student, dto);
                    return dto;
                })
                .toList();

    }

}
