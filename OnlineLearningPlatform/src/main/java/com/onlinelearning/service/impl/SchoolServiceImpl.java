package com.onlinelearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinelearning.dto.schoolDTOs.SchoolRequestDTO;
import com.onlinelearning.dto.schoolDTOs.SchoolResponceDTO;
import com.onlinelearning.entity.School;
import com.onlinelearning.exception.NotFoundException;
import com.onlinelearning.repository.SchoolRepo;
import com.onlinelearning.service.SchoolService;

@Service
public class SchoolServiceImpl implements SchoolService {
	@Autowired
	private SchoolRepo schoolRepo;

	public School mapToEntity(SchoolRequestDTO dto, Long id) {
		School school = (id != null) ? schoolRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("School with ID : " + id + " NOT FOUND")) : new School();

		school.setName(dto.getName());
		school.setDescription(dto.getDescription());
		school.setAddress(dto.getAddress());

		return school;
	}

	public SchoolResponceDTO mapToResponce(School school) {
		if (school == null) {
			return null;
		}

		SchoolResponceDTO dto = new SchoolResponceDTO();

		dto.setId(school.getId());
		dto.setName(school.getName());
		dto.setDescription(school.getDescription());
		dto.setAddress(school.getAddress());

		return dto;
	}

	@Override
	public SchoolResponceDTO addSchool(SchoolRequestDTO dto) {
		School school = mapToEntity(dto, null);

		school = schoolRepo.save(school);

		return mapToResponce(school);
	}

	@Override
	public List<SchoolResponceDTO> getAllSchools() {
		List<School> schools = schoolRepo.findAll();

		List<SchoolResponceDTO> dtos = new ArrayList<>();

		for (School school : schools) {
			dtos.add(mapToResponce(school));
		}

		return dtos;
	}

	@Override
	public SchoolResponceDTO getSchoolById(Long id) {
		School school = schoolRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("School with ID : " + id + " NOT FOUND"));

		return mapToResponce(school);
	}

	@Override
	public SchoolResponceDTO updateSchool(Long id, SchoolRequestDTO dto) {
		School school = mapToEntity(dto, id);

		school = schoolRepo.save(school);

		return mapToResponce(school);
	}

	@Override
	public void deleteSchool(Long id) {
		schoolRepo.findById(id).orElseThrow(() -> new NotFoundException("School with ID : " + id + " NOT FOUND"));

		schoolRepo.deleteById(id);
	}

}
