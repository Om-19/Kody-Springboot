package com.onlinelearning.dto.courseDTOs;

import com.onlinelearning.entity.Category;
import com.onlinelearning.entity.Instructor;
import com.onlinelearning.entity.School;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponceDTO {
	private Long id;
	private String title;
	private String description;
	private Double price;
	private String level;
	private Integer durationHours;
	private Category category;
	private School school;
	private Instructor instructor;
}
