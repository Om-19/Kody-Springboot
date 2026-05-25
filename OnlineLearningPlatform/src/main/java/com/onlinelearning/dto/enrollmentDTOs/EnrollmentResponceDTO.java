package com.onlinelearning.dto.enrollmentDTOs;

import java.time.LocalDate;
import com.onlinelearning.entity.Course;
import com.onlinelearning.entity.Student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentResponceDTO {
	private Long id;
	private LocalDate enrollmentDate;
	private Student student;
	private Course course;
}
