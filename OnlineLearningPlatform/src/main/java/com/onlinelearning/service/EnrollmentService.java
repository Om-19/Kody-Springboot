package com.onlinelearning.service;

import java.util.List;

import com.onlinelearning.dto.enrollmentDTOs.EnrollmentRequestDTO;
import com.onlinelearning.dto.enrollmentDTOs.EnrollmentResponceDTO;

public interface EnrollmentService {
	EnrollmentResponceDTO addEnrollment(EnrollmentRequestDTO dto);

	    List<EnrollmentResponceDTO> getAllEnrollments();

	    EnrollmentResponceDTO getEnrollmentById(Long id);

	    EnrollmentResponceDTO updateEnrollment(Long id,EnrollmentRequestDTO dto);

	    void deleteEnrollment(Long id);
}
