package com.onlinelearning.dto.instructorDTOs;

import com.onlinelearning.entity.School;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorResponceDTO {
	private Long id;
	private String name;
	private String email;
	private String expertise;
	private Integer experienceYears;
	private School school;
}
