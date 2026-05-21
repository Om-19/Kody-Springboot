package com.olp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
