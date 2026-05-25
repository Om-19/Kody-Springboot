package com.onlinelearning.dto.studentDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDTO {
	@NotBlank(message = "Name is reuired")
	private String name;
	@NotBlank(message = "Email is reuired")
	@Email(message = "Email must in proper format")
	private String email;
	@NotBlank(message = "Education is reuired")
	private String education;
}
