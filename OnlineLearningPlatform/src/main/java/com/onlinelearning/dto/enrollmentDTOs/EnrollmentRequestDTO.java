package com.onlinelearning.dto.enrollmentDTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentRequestDTO {
	@NotNull(message = "Enrollment Date is required.")
	private LocalDate enrollmentDate;
	@NotNull(message = "Student ID is required.")
	private Long studentId;
	@NotNull(message = "Course ID Date is required.")
	private Long courseId;
}
