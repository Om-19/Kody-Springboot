package com.onlinelearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinelearning.dto.instructorDTOs.InstructorRequestDTO;
import com.onlinelearning.dto.instructorDTOs.InstructorResponceDTO;
import com.onlinelearning.entity.Instructor;
import com.onlinelearning.exception.NotFoundException;
import com.onlinelearning.repository.InstructorRepo;
import com.onlinelearning.repository.SchoolRepo;
import com.onlinelearning.service.InstructorService;

@Service
public class InstructorServiceImpl implements InstructorService {
	@Autowired
	private InstructorRepo instructorRepo;
	@Autowired
	private SchoolRepo schoolRepo;

	public Instructor mapToEntity(InstructorRequestDTO dto, Long id) {
		Instructor instructor = (id != null) ? instructorRepo.findById(id).orElseThrow(
				() -> new NotFoundException("Instructor with ID : " + id + " NOT FOUND")) : new Instructor();

		instructor.setName(dto.getName());
		instructor.setEmail(dto.getEmail());
		instructor.setExpertise(dto.getExpertise());
		instructor.setExperienceYears(dto.getExperienceYears());

		instructor.setSchool(schoolRepo.findById(dto.getSchoolId())
				.orElseThrow(() -> new NotFoundException("School with ID : " + dto.getSchoolId() + " NOT FOUND")));

		return instructor;
	}

	public InstructorResponceDTO mapToResponce(Instructor instructor) {
		if (instructor == null) {
			return null;
		}

		InstructorResponceDTO dto = new InstructorResponceDTO();

		dto.setId(instructor.getId());
		dto.setName(instructor.getName());
		dto.setEmail(instructor.getEmail());
		dto.setExpertise(instructor.getExpertise());
		dto.setExperienceYears(instructor.getExperienceYears());

		dto.setSchool(instructor.getSchool());

		return dto;
	}

	@Override
	public InstructorResponceDTO addInstructor(InstructorRequestDTO dto) {
		Instructor instructor = mapToEntity(dto, null);

		instructor = instructorRepo.save(instructor);

		return mapToResponce(instructor);
	}

	@Override
	public List<InstructorResponceDTO> getAllInstructors() {
		List<Instructor> instructors = instructorRepo.findAll();

		List<InstructorResponceDTO> dtos = new ArrayList<>();

		for (Instructor instructor : instructors) {
			dtos.add(mapToResponce(instructor));
		}

		return dtos;
	}

	@Override
	public InstructorResponceDTO getInstructorById(Long id) {
		Instructor instructor = instructorRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Instructor with ID : " + id + " NOT FOUND"));

		return mapToResponce(instructor);
	}

	@Override
	public InstructorResponceDTO updateInstructor(Long id, InstructorRequestDTO dto) {
		Instructor instructor = mapToEntity(dto, id);

		instructor = instructorRepo.save(instructor);

		return mapToResponce(instructor);
	}

	@Override
	public void deleteInstructor(Long id) {
		instructorRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Instructor with ID : " + id + " NOT FOUND"));

		instructorRepo.deleteById(id);
	}

}
