package com.jpa.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.main.entity.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

}
