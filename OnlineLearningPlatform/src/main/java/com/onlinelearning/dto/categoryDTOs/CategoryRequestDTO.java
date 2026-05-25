package com.onlinelearning.dto.categoryDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {
	@NotBlank(message = "Category name is required")
	private String name;
	@NotBlank(message = "Category description is required")
	private String description;
}
