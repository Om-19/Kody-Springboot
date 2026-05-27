package com.olp.service;

import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.olp.entity.Course;
import com.olp.entity.Enrollment;
import com.olp.entity.Student;
import com.olp.entity.enums.EnrollmentStatus;
import com.olp.repository.CoursesRepository;
import com.olp.repository.EnrollmentRepository;
import com.olp.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CoursesRepository coursesRepository;

    public String enrollCourse(Long courseId, Authentication authentication) {

        // Get logged in user
        String email = authentication.getName();
        Student student = studentRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException(
                        "Student not found"));

        // Fetch Course
        Course course = coursesRepository
                .findById(courseId)
                .orElseThrow(() -> new RuntimeException(
                        "Course not found"));

        // Check Already Enrolled
        boolean alreadyEnrolled = enrollmentRepository
                .findByStudentIdAndCourseId(
                        student.getId(),
                        course.getId())
                .isPresent();

        if (alreadyEnrolled) {

            throw new RuntimeException(
                    "Already enrolled in course");
        }
        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .status(
                        EnrollmentStatus.ACTIVE)
                .enrolledAt(
                        LocalDateTime.now())
                .build();

        enrollmentRepository.save(enrollment);

        return "Course enrolled successfully";
    }

}
