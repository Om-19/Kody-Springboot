package com.olp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olp.entity.Enrollment;
import com.olp.entity.Student;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Optional<Student> findByStudentIdAndCourseId(Long id, Long id2);

    Optional<Enrollment> findByStudentIdAndCourseId(
            Long studentId,
            Long courseId);

    boolean existsByStudentIdAndCourseId(
            Long studentId,
            Long courseId);

    @Query("""
            SELECT e.student
            FROM Enrollment e
            WHERE e.course.id = :courseId
            """)
    List<Student> getAllStudentForCourse(Long courseId);

}
