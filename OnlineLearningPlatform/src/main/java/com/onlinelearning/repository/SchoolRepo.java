package com.onlinelearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinelearning.entity.School;

@Repository
public interface SchoolRepo extends JpaRepository<School, Long>{

}
