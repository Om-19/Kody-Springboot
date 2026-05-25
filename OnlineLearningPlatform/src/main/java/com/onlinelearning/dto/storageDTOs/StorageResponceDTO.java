package com.onlinelearning.dto.storageDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageResponceDTO {
	private Long id;
	private String fileName;
	private String fileType;
	private String fileUrl;
}
