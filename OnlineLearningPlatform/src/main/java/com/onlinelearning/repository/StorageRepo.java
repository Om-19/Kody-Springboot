package com.onlinelearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinelearning.entity.Storage;

@Repository
public interface StorageRepo extends JpaRepository<Storage, Long>{

}
