package com.onlinelearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.onlinelearning.dto.enrollmentDTOs.EnrollmentRequestDTO;
import com.onlinelearning.dto.enrollmentDTOs.EnrollmentResponceDTO;
import com.onlinelearning.entity.Enrollment;
import com.onlinelearning.exception.NotFoundException;
import com.onlinelearning.repository.CourseRepo;
import com.onlinelearning.repository.EnrollmentRepo;
import com.onlinelearning.repository.StudentRepo;
import com.onlinelearning.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
	private EnrollmentRepo enrollmentRepo;
	private StudentRepo studentRepo;
	private CourseRepo courseRepo;

	public Enrollment mapToEntity(EnrollmentRequestDTO dto, Long id) {
		Enrollment enrollment = (id != null) ? enrollmentRepo.findById(id).orElseThrow(
				() -> new NotFoundException("Enrollment with ID : " + id + " NOT FOUND")) : new Enrollment();

		enrollment.setEnrollmentDate(dto.getEnrollmentDate());

		enrollment.setStudent(studentRepo.findById(dto.getStudentId())
				.orElseThrow(() -> new NotFoundException("Student with ID : " + dto.getStudentId() + " NOT FOUND")));

		enrollment.setCourse(courseRepo.findById(dto.getCourseId())
				.orElseThrow(() -> new NotFoundException("Course with ID : " + dto.getCourseId() + " NOT FOUND")));
		return enrollment;
	}

	public EnrollmentResponceDTO mapToResponce(Enrollment enrollment) {
		if (enrollment == null) {
			return null;
		}

		EnrollmentResponceDTO dto = new EnrollmentResponceDTO();

		dto.setId(enrollment.getId());

		dto.setEnrollmentDate(enrollment.getEnrollmentDate());

		dto.setStudent(enrollment.getStudent());

		dto.setCourse(enrollment.getCourse());

		return dto;
	}

	@Override
	public EnrollmentResponceDTO addEnrollment(EnrollmentRequestDTO dto) {
		Enrollment enrollment = mapToEntity(dto, null);

		enrollment = enrollmentRepo.save(enrollment);

		return mapToResponce(enrollment);
	}

	@Override
	public List<EnrollmentResponceDTO> getAllEnrollments() {
		List<Enrollment> enrollments = enrollmentRepo.findAll();

		List<EnrollmentResponceDTO> dtos = new ArrayList<>();

		for (Enrollment enrollment : enrollments) {
			dtos.add(mapToResponce(enrollment));
		}

		return dtos;
	}

	@Override
	public EnrollmentResponceDTO getEnrollmentById(Long id) {
		Enrollment enrollment = enrollmentRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Course with ID : " + id + " NOT FOUND"));

		return mapToResponce(enrollment);
	}

	@Override
	public EnrollmentResponceDTO updateEnrollment(Long id, EnrollmentRequestDTO dto) {
		Enrollment enrollment = mapToEntity(dto, id);

		enrollment = enrollmentRepo.save(enrollment);

		return mapToResponce(enrollment);
	}

	@Override
	public void deleteEnrollment(Long id) {
		enrollmentRepo.findById(id).orElseThrow(() -> new NotFoundException("Course with ID : " + id + " NOT FOUND"));

		enrollmentRepo.deleteById(id);
	}

}
