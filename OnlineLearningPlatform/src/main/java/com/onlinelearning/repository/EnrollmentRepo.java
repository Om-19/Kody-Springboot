package com.onlinelearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinelearning.entity.Enrollment;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Long>{

}
