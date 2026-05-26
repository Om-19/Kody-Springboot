package com.onlinelearning.dto.schoolDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolRequestDTO {
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Description is required")
	private String description;
	@NotBlank(message = "Address is required")
	private String address;
}
