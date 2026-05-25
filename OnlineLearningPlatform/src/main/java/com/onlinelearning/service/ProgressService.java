package com.onlinelearning.service;

import java.util.List;

import com.onlinelearning.dto.progressDTOs.ProgresRequestDTO;
import com.onlinelearning.dto.progressDTOs.ProgresResponceDTO;

public interface ProgressService {
	ProgresResponceDTO addProgress(ProgresRequestDTO dto);

    List<ProgresResponceDTO> getAllProgress();

    ProgresResponceDTO getProgressById(Long id);

    ProgresResponceDTO updateProgress(Long id,ProgresRequestDTO dto);

    void deleteProgress(Long id);
}
