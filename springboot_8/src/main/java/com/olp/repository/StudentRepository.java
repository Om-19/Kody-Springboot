package com.olp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Prevents NullPointerException.
    Optional<Student> findByEmail(String email);
}
