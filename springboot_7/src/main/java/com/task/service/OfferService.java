package com.task.service;

import com.task.dto.request.OfferRequestDto;
import com.task.dto.response.OfferResponseDto;

public interface OfferService {
    OfferResponseDto createOffer(OfferRequestDto offerRequestDto);
}
