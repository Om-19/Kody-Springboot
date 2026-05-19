package com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.entity.Offer;
import com.task.entity.OfferUsage;

import java.util.Optional;

public interface OfferUsageRepository
        extends JpaRepository<OfferUsage, Long> {

    Optional<OfferUsage> findByCustomerEmailAndOffer(
            String customerEmail,
            Offer offer);
}