package com.onlinelearning.dto.reviewDTOs;

import com.onlinelearning.entity.Course;
import com.onlinelearning.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponceDTO {
	private Long id;
	private Integer rating;
	private String comment;
	private Student student;
	private Course course;
}
