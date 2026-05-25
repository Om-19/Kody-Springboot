package com.onlinelearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinelearning.dto.storageDTOs.StorageRequestDTO;
import com.onlinelearning.dto.storageDTOs.StorageResponceDTO;
import com.onlinelearning.entity.Storage;
import com.onlinelearning.exception.NotFoundException;
import com.onlinelearning.repository.CourseRepo;
import com.onlinelearning.repository.StorageRepo;
import com.onlinelearning.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService {
	@Autowired
	private StorageRepo storageRepo;
	@Autowired
	private CourseRepo courseRepo;

	public Storage mapToEntity(StorageRequestDTO dto, Long id) {
		Storage storage = (id != null) ? storageRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Storage with ID : " + id + " NOT FOUND")) : new Storage();

		storage.setFileName(dto.getFileName());

		storage.setFileType(dto.getFileType());

		storage.setFileUrl(dto.getFileUrl());

		storage.setCourse(courseRepo.findById(dto.getCourseId())
				.orElseThrow(() -> new NotFoundException("Course with ID : " + dto.getCourseId() + " NOT FOUND")));

		return storage;
	}

	public StorageResponceDTO mapToResponce(Storage storage) {
		if (storage == null) {
			return null;
		}

		StorageResponceDTO dto = new StorageResponceDTO();

		dto.setId(storage.getId());

		dto.setFileName(storage.getFileName());

		dto.setFileType(storage.getFileType());

		dto.setFileUrl(storage.getFileUrl());

		return dto;
	}

	@Override
	public StorageResponceDTO addStorage(StorageRequestDTO dto) {
		Storage storage = mapToEntity(dto, null);

		storage = storageRepo.save(storage);

		return mapToResponce(storage);
	}

	@Override
	public List<StorageResponceDTO> getAllStorageFiles() {
		List<Storage> storages = storageRepo.findAll();

		List<StorageResponceDTO> dtos = new ArrayList<>();

		for (Storage storage : storages) {
			dtos.add(mapToResponce(storage));
		}

		return dtos;
	}

	@Override
	public StorageResponceDTO getStorageById(Long id) {
		Storage storage = storageRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Storage with ID : " + id + " NOT FOUND"));

		return mapToResponce(storage);
	}

	@Override
	public StorageResponceDTO updateStorage(Long id, StorageRequestDTO dto) {
		Storage storage = mapToEntity(dto, id);

		storage = storageRepo.save(storage);

		return mapToResponce(storage);
	}

	@Override
	public void deleteStorage(Long id) {
		storageRepo.findById(id).orElseThrow(() -> new NotFoundException("Storage with ID : " + id + " NOT FOUND"));

		storageRepo.deleteById(id);
	}

}
