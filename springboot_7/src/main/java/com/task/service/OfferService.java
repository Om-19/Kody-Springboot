package com.task.service;

import org.springframework.data.domain.Page;

import com.task.dto.request.OfferRequestDto;
import com.task.dto.response.OfferResponseDto;

public interface OfferService {
    OfferResponseDto createOffer(OfferRequestDto offerRequestDto);

    void deleteOffer(Long id);

    Page<OfferResponseDto> getAllOffers(int page);
}
