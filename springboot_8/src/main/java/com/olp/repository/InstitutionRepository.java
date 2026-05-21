package com.olp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.entity.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

}
