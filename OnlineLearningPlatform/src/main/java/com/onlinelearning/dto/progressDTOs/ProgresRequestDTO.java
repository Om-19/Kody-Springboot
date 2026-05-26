package com.onlinelearning.dto.progressDTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgresRequestDTO {
	@NotNull
	@Min(value = 0,message = "Completed Percentage can not less than 0")
	@Max(value = 100,message = "Completed Percentage can not greater than 100")
	private Double completedPercentage;
	@NotNull
	private Integer completedLectures;
	@NotBlank
	private String status;
	@NotNull
	private Long studentId;
	@NotNull
	private Long courseId;
}
