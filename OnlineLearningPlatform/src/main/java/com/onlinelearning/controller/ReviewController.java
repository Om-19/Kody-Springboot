package com.onlinelearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinelearning.dto.reviewDTOs.ReviewRequestDTO;
import com.onlinelearning.dto.reviewDTOs.ReviewResponceDTO;
import com.onlinelearning.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<ReviewResponceDTO> saveCategory(@Valid @RequestBody ReviewRequestDTO dto){
		
		return new ResponseEntity<ReviewResponceDTO>(reviewService.addReview(dto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ReviewResponceDTO>> getAllCategories(){
		List<ReviewResponceDTO> dtos = reviewService.getAllReviews();
		if(dtos.size() > 0) {
			return new ResponseEntity<List<ReviewResponceDTO>>(dtos,HttpStatus.FOUND);
		}
		
		return new ResponseEntity<List<ReviewResponceDTO>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReviewResponceDTO> getCategoryById(@PathVariable long id){
		
		return new ResponseEntity<ReviewResponceDTO>(reviewService.getReviewById(id),HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ReviewResponceDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody ReviewRequestDTO dto){
		
		return new ResponseEntity<ReviewResponceDTO>(reviewService.updateReview(id,dto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		reviewService.deleteReview(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
