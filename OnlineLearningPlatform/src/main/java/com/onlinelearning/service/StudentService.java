package com.onlinelearning.service;

import java.util.List;

import com.onlinelearning.dto.studentDTOs.StudentRequestDTO;
import com.onlinelearning.dto.studentDTOs.StudentResponceDTO;

public interface StudentService {
	 StudentResponceDTO addStudent(StudentRequestDTO dto);

	    List<StudentResponceDTO> getAllStudents();

	    StudentResponceDTO getStudentById(Long id);

	    StudentResponceDTO updateStudent(Long id,StudentRequestDTO dto);

	    void deleteStudent(Long id);
}
