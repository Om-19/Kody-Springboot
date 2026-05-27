package com.olp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.entity.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    // Single Optimised Join Query
    @EntityGraph(attributePaths = {
            "courses",
            "instructors"
    })
    List<Institution> findAll();

}
