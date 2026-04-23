package com.jpa.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.main.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
