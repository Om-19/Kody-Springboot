package com.olp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olp.dto.response.GenericResponse;
import com.olp.service.InstitutionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/institutions")
@RequiredArgsConstructor
@Slf4j
public class InstitutionController {

    private final InstitutionService institutionService;

    /*
     * Entity grapg will solve DATABASE FETCHING problem by using optimised join
     * query
     * DTO will control response shape
     */
    @GetMapping
    public ResponseEntity<GenericResponse<?>> getAll() {

        log.info("\nGet All Institution constructor called...");

        return ResponseEntity.ok(
                GenericResponse.builder()
                        .statusCode(200)
                        .message("ALl Institution List")
                        .data(institutionService.getAll())
                        .build());
    }

}
