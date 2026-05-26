package com.onlinelearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinelearning.dto.reviewDTOs.ReviewRequestDTO;
import com.onlinelearning.dto.reviewDTOs.ReviewResponceDTO;
import com.onlinelearning.entity.Review;
import com.onlinelearning.exception.NotFoundException;
import com.onlinelearning.repository.CourseRepo;
import com.onlinelearning.repository.ReviewRepo;
import com.onlinelearning.repository.StudentRepo;
import com.onlinelearning.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewRepo reviewRepo;
	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private CourseRepo courseRepo;

	public Review mapToEntity(ReviewRequestDTO dto, Long id) {
		Review review = (id != null) ? reviewRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Review with ID : " + id + " NOT FOUND")) : new Review();

		review.setRating(dto.getRating());

		review.setComment(dto.getComment());

		review.setStudent(studentRepo.findById(dto.getStudentId())
				.orElseThrow(() -> new NotFoundException("Student with ID : " + dto.getStudentId() + " NOT FOUND")));

		review.setCourse(courseRepo.findById(dto.getCourseId())
				.orElseThrow(() -> new NotFoundException("Course with ID : " + dto.getCourseId() + " NOT FOUND")));

		return review;
	}

	public ReviewResponceDTO mapToResponce(Review review) {
		if (review == null) {
			return null;
		}

		ReviewResponceDTO dto = new ReviewResponceDTO();

		dto.setId(review.getId());

		dto.setRating(review.getRating());

		dto.setComment(review.getComment());

		dto.setStudent(review.getStudent());

		dto.setCourse(review.getCourse());

		return dto;
	}

	@Override
	public ReviewResponceDTO addReview(ReviewRequestDTO dto) {
		Review review = mapToEntity(dto, null);

		review = reviewRepo.save(review);

		return mapToResponce(review);
	}

	@Override
	public List<ReviewResponceDTO> getAllReviews() {
		List<Review> reviews = reviewRepo.findAll();

		List<ReviewResponceDTO> dtos = new ArrayList<>();

		for (Review review : reviews) {
			dtos.add(mapToResponce(review));
		}

		return dtos;
	}

	@Override
	public ReviewResponceDTO getReviewById(Long id) {
		Review review = reviewRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Review with ID : " + id + " NOT FOUND"));

		return mapToResponce(review);
	}

	@Override
	public ReviewResponceDTO updateReview(Long id, ReviewRequestDTO dto) {
		Review review = mapToEntity(dto, id);

		review = reviewRepo.save(review);

		return mapToResponce(review);
	}

	@Override
	public void deleteReview(Long id) {
		reviewRepo.findById(id).orElseThrow(() -> new NotFoundException("Review with ID : " + id + " NOT FOUND"));

		reviewRepo.deleteById(id);
	}

}
