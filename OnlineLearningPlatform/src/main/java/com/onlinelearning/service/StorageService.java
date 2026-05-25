package com.onlinelearning.service;

import java.util.List;

import com.onlinelearning.dto.storageDTOs.StorageRequestDTO;
import com.onlinelearning.dto.storageDTOs.StorageResponceDTO;

public interface StorageService {
	StorageResponceDTO addStorage(StorageRequestDTO dto);

    List<StorageResponceDTO> getAllStorageFiles();

    StorageResponceDTO getStorageById(Long id);

    StorageResponceDTO updateStorage(Long id,StorageRequestDTO dto);

    void deleteStorage(Long id);
}
