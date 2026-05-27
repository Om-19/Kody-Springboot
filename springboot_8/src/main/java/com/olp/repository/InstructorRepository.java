package com.olp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    boolean existsByEmail(String email);

    Optional<Instructor> findByEmail(String email);

    // Object getAllStudentForCourse(Long id);
}
