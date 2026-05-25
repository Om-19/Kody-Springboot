package com.olp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.entity.Course;

public interface CoursesRepository extends JpaRepository<Course, Long> {

    boolean existsBySubjectCode(String subjectCode);

    boolean existsByName(String name);
}
