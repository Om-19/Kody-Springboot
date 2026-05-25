package com.onlinelearning.dto.courseDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequestDTO {
	@NotBlank(message = "Title is required")
	private String title;
	@NotBlank(message = "Description is required")
	private String description;
	@Positive(message = "Price must be positive value")
	@NotNull(message = "Price is required")
	private Double price;
	@NotBlank(message = "Level is required")
	private String level;
	@NotNull(message = "Duration Hours is required")
	private Integer durationHours;
	@NotNull(message = "Category is required")
	private Long categoryId;
	@NotNull(message = "School is required")
	private Long schoolId;
	@NotNull(message = "Instructor is required")
	private Long instructorId;
}
