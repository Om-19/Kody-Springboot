package com.onlinelearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinelearning.dto.studentDTOs.StudentRequestDTO;
import com.onlinelearning.dto.studentDTOs.StudentResponceDTO;
import com.onlinelearning.entity.Student;
import com.onlinelearning.exception.NotFoundException;
import com.onlinelearning.repository.StudentRepo;
import com.onlinelearning.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepo studentRepo;

	public Student mapToEntity(StudentRequestDTO dto, Long id) {
		Student student = (id != null) ? studentRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Student with ID : " + id + " NOT FOUND")) : new Student();

		student.setName(dto.getName());
		student.setEmail(dto.getEmail());
		student.setEducation(dto.getEducation());

		return student;
	}

	public StudentResponceDTO mapToResponce(Student student) {
		if (student == null) {
			return null;
		}

		StudentResponceDTO dto = new StudentResponceDTO();

		dto.setId(student.getId());
		dto.setName(student.getName());
		dto.setEmail(student.getEmail());
		dto.setEducation(student.getEducation());

		return dto;
	}

	@Override
	public StudentResponceDTO addStudent(StudentRequestDTO dto) {
		Student student = mapToEntity(dto, null);

		student = studentRepo.save(student);

		return mapToResponce(student);
	}

	@Override
	public List<StudentResponceDTO> getAllStudents() {
		List<Student> students = studentRepo.findAll();

		List<StudentResponceDTO> dtos = new ArrayList<>();

		for (Student student : students) {
			dtos.add(mapToResponce(student));
		}

		return dtos;
	}

	@Override
	public StudentResponceDTO getStudentById(Long id) {
		Student student = studentRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Student with ID : " + id + " NOT FOUND"));

		return mapToResponce(student);
	}

	@Override
	public StudentResponceDTO updateStudent(Long id, StudentRequestDTO dto) {
		Student student = mapToEntity(dto, id);

		student = studentRepo.save(student);

		return mapToResponce(student);
	}

	@Override
	public void deleteStudent(Long id) {
		studentRepo.findById(id).orElseThrow(() -> new NotFoundException("Student with ID : " + id + " NOT FOUND"));

		studentRepo.deleteById(id);
	}

}
