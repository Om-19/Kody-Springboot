package com.olp.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.olp.dto.request.ReviewDto;
import com.olp.entity.Course;
import com.olp.entity.Review;
import com.olp.entity.Student;
import com.olp.repository.CoursesRepository;
import com.olp.repository.EnrollmentRepository;
import com.olp.repository.ReviewRepository;
import com.olp.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StudentRepository studentRepository;
    private final CoursesRepository coursesRepository;
    private final EnrollmentRepository enrollmentRepository;

    public String saveReview(ReviewDto dto, Authentication authentication) {

        // Get Logged in user
        String email = authentication.getName();
        Student student = studentRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not FOund"));

        // Fetch Course
        Course course = coursesRepository
                .findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course Not Found"));

        boolean alreadyReviewed = reviewRepository
                .findByStudentIdAndCourseId(
                        student.getId(),
                        course.getId())
                .isPresent();

        if (alreadyReviewed) {
            throw new RuntimeException("You Have Already Reviewed the course.");
        }

        boolean enrolled = enrollmentRepository
                .existsByStudentIdAndCourseId(
                        student.getId(),
                        course.getId());

        if (!enrolled) {
            throw new RuntimeException(
                    "You must enroll in the course before reviewing");
        }

        Review review = Review.builder()
                .rating(dto.getRating())
                .comment(dto.getComment())
                .student(student)
                .course(course)
                .build();

        reviewRepository.save(review);

        return "Review Made Successfully.";
    }

}
