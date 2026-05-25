package com.onlinelearning.service;

import java.util.List;

import com.onlinelearning.dto.instructorDTOs.InstructorRequestDTO;
import com.onlinelearning.dto.instructorDTOs.InstructorResponceDTO;

public interface InstructorService {
	InstructorResponceDTO addInstructor(InstructorRequestDTO dto);

    List<InstructorResponceDTO> getAllInstructors();

    InstructorResponceDTO getInstructorById(Long id);

    InstructorResponceDTO updateInstructor(Long id,InstructorRequestDTO dto);

    void deleteInstructor(Long id);
}
