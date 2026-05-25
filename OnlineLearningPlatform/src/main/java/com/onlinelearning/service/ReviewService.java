package com.onlinelearning.service;

import java.util.List;

import com.onlinelearning.dto.reviewDTOs.ReviewRequestDTO;
import com.onlinelearning.dto.reviewDTOs.ReviewResponceDTO;

public interface ReviewService {
	ReviewResponceDTO addReview(ReviewRequestDTO dto);

    List<ReviewResponceDTO> getAllReviews();

    ReviewResponceDTO getReviewById(Long id);

    ReviewResponceDTO updateReview(Long id,ReviewRequestDTO dto);

    void deleteReview(Long id);
}
