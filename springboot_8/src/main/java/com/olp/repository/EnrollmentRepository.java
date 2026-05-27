package com.olp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.entity.Enrollment;
import com.olp.entity.Student;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Optional<Student> findByStudentIdAndCourseId(Long id, Long id2);

    boolean existsByStudentIdAndCourseId(
            Long studentId,
            Long courseId);
}
