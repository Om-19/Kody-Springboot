package com.onlinelearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.onlinelearning.dto.courseDTOs.CourseRequestDTO;
import com.onlinelearning.dto.courseDTOs.CourseResponceDTO;
import com.onlinelearning.entity.Course;
import com.onlinelearning.exception.NotFoundException;
import com.onlinelearning.repository.CategoryRepo;
import com.onlinelearning.repository.CourseRepo;
import com.onlinelearning.repository.InstructorRepo;
import com.onlinelearning.repository.SchoolRepo;
import com.onlinelearning.service.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

	private CourseRepo courseRepo;
	private CategoryRepo categoryRepo;
	private SchoolRepo schoolRepo;
	private InstructorRepo instructorRepo;

	public Course mapToEntity(CourseRequestDTO dto, Long id) {
		Course course = (id != null) ? courseRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Course with ID : " + id + " NOT FOUND")) : new Course();

		course.setTitle(dto.getTitle());
		course.setDescription(dto.getDescription());
		course.setPrice(dto.getPrice());
		course.setLevel(dto.getLevel());
		course.setDurationHours(dto.getDurationHours());
		course.setCategory(categoryRepo.findById(dto.getCategoryId())
				.orElseThrow(() -> new NotFoundException("Category with ID : " + dto.getCategoryId() + " NOT FOUND")));
		course.setSchool(schoolRepo.findById(dto.getSchoolId())
				.orElseThrow(() -> new NotFoundException("School with ID : " + dto.getSchoolId() + " NOT FOUND")));
		course.setInstructor(instructorRepo.findById(dto.getInstructorId()).orElseThrow(
				() -> new NotFoundException("Instructor with ID : " + dto.getInstructorId() + " NOT FOUND")));

		return course;
	}

	public CourseResponceDTO mapToResponce(Course course) {
		if (course == null) {
			return null;
		}
		CourseResponceDTO dto = new CourseResponceDTO();

		dto.setId(course.getId());
		dto.setTitle(course.getTitle());
		dto.setDescription(course.getDescription());
		dto.setPrice(course.getPrice());
		dto.setLevel(course.getLevel());
		dto.setDurationHours(course.getDurationHours());
		dto.setCategory(course.getCategory());
		dto.setSchool(course.getSchool());
		dto.setInstructor(course.getInstructor());

		return dto;
	}

	@Override
	public CourseResponceDTO saveCourse(CourseRequestDTO dto) {
		Course course = mapToEntity(dto, null);

		course = courseRepo.save(course);

		return mapToResponce(course);
	}

	@Override
	public List<CourseResponceDTO> getAllCourses() {
		List<Course> courses = courseRepo.findAll();

		List<CourseResponceDTO> courseResponceDTOs = new ArrayList<>();

		for (Course course : courses) {
			courseResponceDTOs.add(mapToResponce(course));
		}

		return courseResponceDTOs;
	}

	@Override
	public CourseResponceDTO findCourseById(Long id) {
		Course course = courseRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Course with ID : " + id + " NOT FOUND"));

		return mapToResponce(course);
	}

	@Override
	public CourseResponceDTO updateCourse(Long id, CourseRequestDTO dto) {
		Course course = mapToEntity(dto, id);

		course = courseRepo.save(course);

		return mapToResponce(course);
	}

	@Override
	public void deleteCourse(Long id) {
		courseRepo.findById(id).orElseThrow(() -> new NotFoundException("Course with ID : " + id + " NOT FOUND"));

		courseRepo.deleteById(id);
	}

}
