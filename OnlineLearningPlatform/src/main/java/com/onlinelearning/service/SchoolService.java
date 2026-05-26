package com.onlinelearning.service;

import java.util.List;

import com.onlinelearning.dto.schoolDTOs.SchoolRequestDTO;
import com.onlinelearning.dto.schoolDTOs.SchoolResponceDTO;

public interface SchoolService {
	 SchoolResponceDTO addSchool(SchoolRequestDTO dto);

	    List<SchoolResponceDTO> getAllSchools();

	    SchoolResponceDTO getSchoolById(Long id);

	    SchoolResponceDTO updateSchool(Long id,SchoolRequestDTO dto);

	    void deleteSchool(Long id);
}
