package com.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.entity.OfferUsage;

public interface OfferUsageRepository
                extends JpaRepository<OfferUsage, Long> {

        // How many times has THIS customer used THIS offer?
        Optional<OfferUsage> findByCustomerEmailAndOffer_offerId(
                        String customerEmail,
                        Long offerId);
}