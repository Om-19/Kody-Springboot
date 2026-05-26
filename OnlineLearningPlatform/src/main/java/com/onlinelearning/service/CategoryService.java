package com.onlinelearning.service;

import java.util.List;

import com.onlinelearning.dto.categoryDTOs.CategoryRequestDTO;
import com.onlinelearning.dto.categoryDTOs.CategoryResponceDTO;

public interface CategoryService {
	CategoryResponceDTO saveCategory(CategoryRequestDTO dto);
	
	List<CategoryResponceDTO> getAllCategories();
	
	CategoryResponceDTO getCategoryById(Long id);
	
	CategoryResponceDTO updateCategory(Long id,CategoryRequestDTO dto);
	
	void deleteCategory(Long id);
}
