package com.jpa.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.main.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
