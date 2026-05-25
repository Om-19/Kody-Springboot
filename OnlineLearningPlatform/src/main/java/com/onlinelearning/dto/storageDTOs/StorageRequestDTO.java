package com.onlinelearning.dto.storageDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageRequestDTO {
	@NotBlank(message = "File Name is required")
	private String fileName;
	@NotBlank(message = "File Type is required")
	private String fileType;
	@NotBlank(message = "File Url is required")
	private String fileUrl;
	@NotNull(message = "Course ID is required")
	private Long courseId;
}
