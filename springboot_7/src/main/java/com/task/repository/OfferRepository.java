package com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

}