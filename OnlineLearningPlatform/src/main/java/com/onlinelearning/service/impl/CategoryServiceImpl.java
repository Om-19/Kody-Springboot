package com.onlinelearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.onlinelearning.dto.categoryDTOs.CategoryRequestDTO;
import com.onlinelearning.dto.categoryDTOs.CategoryResponceDTO;
import com.onlinelearning.entity.Category;
import com.onlinelearning.exception.NotFoundException;
import com.onlinelearning.repository.CategoryRepo;
import com.onlinelearning.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepo categoryRepo;

	public Category mapToEntity(CategoryRequestDTO dto, Long id) {
		Category category = (id != null) ? categoryRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Category with ID : " + id + " NOT FOUND")) : new Category();

		category.setName(dto.getName());
		category.setDescription(dto.getDescription());

		return category;
	}

	public CategoryResponceDTO mapToResponce(Category category) {
		if (category == null) {
			return null;
		}
		CategoryResponceDTO dto = new CategoryResponceDTO();

		dto.setId(category.getId());
		dto.setName(category.getName());
		dto.setDescription(category.getDescription());

		return dto;
	}

	@Override
	public CategoryResponceDTO saveCategory(CategoryRequestDTO dto) {
		Category category = mapToEntity(dto, null);

		category = categoryRepo.save(category);

		return mapToResponce(category);
	}

	@Override
	public List<CategoryResponceDTO> getAllCategories() {
		List<Category> categories = categoryRepo.findAll();

		List<CategoryResponceDTO> categoryResponceDTOs = new ArrayList<>();

		for (Category category : categories) {
			categoryResponceDTOs.add(mapToResponce(category));
		}

		return categoryResponceDTOs;
	}

	@Override
	public CategoryResponceDTO getCategoryById(Long id) {
		Category category = categoryRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Category with ID : " + id + " NOT FOUND"));

		return mapToResponce(category);

	}

	@Override
	public CategoryResponceDTO updateCategory(Long id, CategoryRequestDTO dto) {
		Category category = mapToEntity(dto, id);

		category = categoryRepo.save(category);

		return mapToResponce(category);
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepo.findById(id).orElseThrow(() -> new NotFoundException("Category with ID : " + id + " NOT FOUND"));

		categoryRepo.deleteById(id);
	}

}
