package com.onlinelearning.service;

import java.util.List;

import com.onlinelearning.dto.courseDTOs.CourseRequestDTO;
import com.onlinelearning.dto.courseDTOs.CourseResponceDTO;

public interface CourseService {
	CourseResponceDTO saveCourse(CourseRequestDTO dto);
	
	List<CourseResponceDTO> getAllCourses();
	
	CourseResponceDTO findCourseById(Long id);
	
	CourseResponceDTO updateCourse(Long id,CourseRequestDTO dto);
	
	void deleteCourse(Long id);
}
