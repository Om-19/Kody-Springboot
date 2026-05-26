package com.onlinelearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinelearning.entity.Progress;

@Repository
public interface ProgressRepo extends JpaRepository<Progress, Long>{

}
