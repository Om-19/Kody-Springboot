package com.task.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.task.dto.request.OfferRequestDto;
import com.task.dto.response.OfferResponseDto;
import com.task.entity.Offer;
import com.task.enums.OfferType;
import com.task.exception.customExc.InvalidOfferException;
import com.task.repository.OfferRepository;
import com.task.service.OfferService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    @Override
    public OfferResponseDto createOffer(OfferRequestDto offerRequestDto) {

        validateOffer(offerRequestDto);

        Offer offer = Offer.builder()
                .offerName(offerRequestDto.getOfferName())
                .offerType(offerRequestDto.getOfferType())
                .offerValue(offerRequestDto.getOfferValue())
                .maxUsagePerCustomer(offerRequestDto.getMaxUsagePerCustomer())
                .maxDiscountValue(offerRequestDto.getMaxDiscountValue())
                .startDate(offerRequestDto.getStartDate())
                .endDate(offerRequestDto.getEndDate())
                .active(true)
                .build();

        Offer savedOffer = offerRepository.save(offer);

        return mapToResponse(savedOffer);
    }

    /*
     * Validate Offer Entity
     */
    private void validateOffer(OfferRequestDto offerRequestDto) {

        // Start date must be before end date
        if (offerRequestDto.getStartDate().isAfter(offerRequestDto.getEndDate())) {
            throw new InvalidOfferException("Start date must be before end date.");
        }

        // Start date should not be in past
        if (offerRequestDto.getStartDate().isBefore(LocalDateTime.now())) {
            throw new InvalidOfferException("Start date cannot be in the past.");
        }
        ;

        // Percentage Validation
        if (offerRequestDto.getOfferType() == OfferType.PERCENTAGE) {
            // "Is offerValue greater than 100?"
            if (offerRequestDto.getOfferValue().compareTo(BigDecimal.valueOf(100)) > 0) {
                throw new InvalidOfferException("Percentage cannot be more than 100");
            }
        }

        // Fixed offer validation
        if (offerRequestDto.getOfferType() == OfferType.FIXED) {

            if (offerRequestDto.getOfferValue()
                    .compareTo(
                            offerRequestDto.getMaxDiscountValue()) > 0) {

                throw new InvalidOfferException(
                        "Fixed offer value cannot exceed max discount value");
            }
        }
    }

    private OfferResponseDto mapToResponse(
            Offer offer) {

        return OfferResponseDto.builder()
                .offerId(offer.getOfferId())
                .offerName(offer.getOfferName())
                .offerType(offer.getOfferType())
                .offerValue(offer.getOfferValue())
                .maxUsagePerCustomer(
                        offer.getMaxUsagePerCustomer())
                .maxDiscountValue(
                        offer.getMaxDiscountValue())
                .startDate(offer.getStartDate())
                .endDate(offer.getEndDate())
                .active(offer.getActive())
                .createdDate(offer.getCreatedDate())
                .updatedDate(offer.getUpdatedDate())
                .build();
    }

}
