package com.onlinelearning.dto.reviewDTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDTO {
	@NotNull(message = "Rating is required")
	@Min(value = 1,message = "Rating can not less than 1")
	@Max(value = 5,message = "Rating can not greater than 5")
	private Integer rating;
	@NotBlank(message = "Comment is required")
	private String comment;
	
	@NotNull
	private Long studentId;
	
	@NotNull
	private Long courseId;
}
