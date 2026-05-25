package com.onlinelearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinelearning.dto.progressDTOs.ProgresRequestDTO;
import com.onlinelearning.dto.progressDTOs.ProgresResponceDTO;
import com.onlinelearning.entity.Progress;
import com.onlinelearning.exception.NotFoundException;
import com.onlinelearning.repository.CourseRepo;
import com.onlinelearning.repository.ProgressRepo;
import com.onlinelearning.repository.StudentRepo;
import com.onlinelearning.service.ProgressService;

@Service
public class ProgressServiceImpl implements ProgressService {
	@Autowired
	private ProgressRepo progressRepo;
	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private CourseRepo courseRepo;

	public Progress mapToEntity(ProgresRequestDTO dto, Long id) {
		Progress progress = (id != null) ? progressRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Progress with ID : " + id + " NOT FOUND")) : new Progress();

		progress.setCompletedPercentage(dto.getCompletedPercentage());

		progress.setCompletedLectures(dto.getCompletedLectures());

		progress.setStatus(dto.getStatus());

		progress.setStudent(studentRepo.findById(dto.getStudentId())
				.orElseThrow(() -> new NotFoundException("Student with ID : " + dto.getStudentId() + " NOT FOUND")));

		progress.setCourse(courseRepo.findById(dto.getCourseId())
				.orElseThrow(() -> new NotFoundException("Course with ID : " + dto.getCourseId() + " NOT FOUND")));
		return progress;
	}

	public ProgresResponceDTO mapToResponce(Progress progress) {
		if (progress == null) {
			return null;
		}

		ProgresResponceDTO dto = new ProgresResponceDTO();

		dto.setId(progress.getId());

		dto.setCompletedPercentage(progress.getCompletedPercentage());

		dto.setCompletedLectures(progress.getCompletedLectures());

		dto.setStatus(progress.getStatus());

		dto.setStudent(progress.getStudent());

		dto.setCourse(progress.getCourse());

		return dto;
	}

	@Override
	public ProgresResponceDTO addProgress(ProgresRequestDTO dto) {
		Progress progress = mapToEntity(dto, null);

		progress = progressRepo.save(progress);

		return mapToResponce(progress);
	}

	@Override
	public List<ProgresResponceDTO> getAllProgress() {
		List<Progress> progresses = progressRepo.findAll();

		List<ProgresResponceDTO> dtos = new ArrayList<>();

		for (Progress progres : progresses) {
			dtos.add(mapToResponce(progres));
		}

		return dtos;
	}

	@Override
	public ProgresResponceDTO getProgressById(Long id) {
		Progress progress = progressRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Progress with ID : " + id + " NOT FOUND"));

		return mapToResponce(progress);
	}

	@Override
	public ProgresResponceDTO updateProgress(Long id, ProgresRequestDTO dto) {
		Progress progress = mapToEntity(dto, id);

		progress = progressRepo.save(progress);

		return mapToResponce(progress);
	}

	@Override
	public void deleteProgress(Long id) {
		progressRepo.findById(id).orElseThrow(() -> new NotFoundException("Progress with ID : " + id + " NOT FOUND"));

		progressRepo.deleteById(id);
	}

}
