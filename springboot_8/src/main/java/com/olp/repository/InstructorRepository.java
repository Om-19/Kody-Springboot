package com.olp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Optional<Instructor> findByEmail(String email);
}
