package com.olp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByStudentIdAndCourseId(
            Long studentId,
            Long courseId);
}
