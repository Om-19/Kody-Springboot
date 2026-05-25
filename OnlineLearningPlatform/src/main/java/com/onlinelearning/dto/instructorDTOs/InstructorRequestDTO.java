package com.onlinelearning.dto.instructorDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorRequestDTO {
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Email is required")
	@Email(message = "Email must be iun proper format")
	private String email;
	@NotBlank(message = "Experties is required")
	private String expertise;
	@NotNull(message = "Experience years is required")
	@PositiveOrZero
	private Integer experienceYears;
	@NotNull(message = "School ID is required")
	private Long schoolId;
}
