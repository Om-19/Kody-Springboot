package com.olp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olp.dto.request.ReviewDto;
import com.olp.dto.response.GenericResponse;
import com.olp.service.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<GenericResponse<?>> postMethodName(@Valid @RequestBody ReviewDto review,
            Authentication authentication) {

        return ResponseEntity.ok(
                GenericResponse.builder()
                        .statusCode(200)
                        .message("Reviews fetched successfully")
                        .data(reviewService.saveReview(review, authentication))
                        .build());
    }

    // GET ALL REVIEWS
    @GetMapping
    public ResponseEntity<GenericResponse<?>> getAllReviews() {

        return ResponseEntity.ok(
                GenericResponse.builder()
                        .statusCode(200)
                        .message("Reviews fetched successfully")
                        .data(reviewService.getAllReviews())
                        .build());
    }

    // GET REVIEW BY ID
    @GetMapping("/{reviewId}")
    public ResponseEntity<GenericResponse<?>> getReviewById(@PathVariable Long reviewId) {

        return ResponseEntity.ok(
                GenericResponse.builder()
                        .statusCode(200)
                        .message("Review fetched successfully")
                        .data(
                                reviewService
                                        .getReviewById(reviewId))
                        .build());
    }

    // UPDATE REVIEW
    @PutMapping("/update/{reviewId}")
    public ResponseEntity<GenericResponse<?>>

            updateReview(
                    @PathVariable Long reviewId,
                    @Valid @RequestBody ReviewDto dto,
                    Authentication authentication) {

        return ResponseEntity.ok(
                GenericResponse.builder()
                        .statusCode(200)
                        .message("Review updated successfully")
                        .data(reviewService.updateReview(
                                reviewId,
                                dto,
                                authentication))

                        .build());
    }

    // DELETE REVIEW
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<GenericResponse<?>>

            deleteReview(
                    @PathVariable Long reviewId,
                    Authentication authentication) {

        reviewService.deleteReview(
                reviewId,
                authentication);

        return ResponseEntity.ok(
                GenericResponse.builder()
                        .statusCode(200)
                        .message("Review deleted successfully")
                        .data(null)
                        .build());
    }

}
