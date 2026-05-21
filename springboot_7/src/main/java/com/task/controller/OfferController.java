package com.task.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.request.OfferRequestDto;
import com.task.dto.response.OfferResponseDto;
import com.task.service.OfferService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("offers")
@RequiredArgsConstructor
public class OfferController {
    private final OfferService offerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OfferResponseDto createOffer(
            @Valid @RequestBody OfferRequestDto requestDTO) {

        return offerService.createOffer(
                requestDTO);
    }

    @GetMapping("/{page}")
    public Page<OfferResponseDto> getAllOffers(@PathVariable int page) {
        return offerService.getAllOffers(page);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOffer(@Valid @PathVariable Long id) {
        offerService.deleteOffer(id);
    }
}
