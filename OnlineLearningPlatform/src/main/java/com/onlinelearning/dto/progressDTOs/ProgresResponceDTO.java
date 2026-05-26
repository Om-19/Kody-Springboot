package com.onlinelearning.dto.progressDTOs;

import com.onlinelearning.entity.Course;
import com.onlinelearning.entity.Student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgresResponceDTO {
	private Long id;
	private Double completedPercentage;
	private Integer completedLectures;
	private String status;
	private Student student;
	private Course course;
}
