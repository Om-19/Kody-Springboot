package com.olp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
