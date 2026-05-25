package com.onlinelearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinelearning.dto.categoryDTOs.CategoryRequestDTO;
import com.onlinelearning.dto.categoryDTOs.CategoryResponceDTO;
import com.onlinelearning.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<CategoryResponceDTO> saveCategory(@Valid @RequestBody CategoryRequestDTO dto){
		
		return new ResponseEntity<CategoryResponceDTO>(categoryService.saveCategory(dto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryResponceDTO>> getAllCategories(){
		List<CategoryResponceDTO> dtos = categoryService.getAllCategories();
		if(dtos.size() > 0) {
			return new ResponseEntity<List<CategoryResponceDTO>>(dtos,HttpStatus.FOUND);
		}
		
		return new ResponseEntity<List<CategoryResponceDTO>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponceDTO> getCategoryById(@PathVariable long id){
		
		return new ResponseEntity<CategoryResponceDTO>(categoryService.getCategoryById(id),HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryResponceDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO dto){
		
		return new ResponseEntity<CategoryResponceDTO>(categoryService.updateCategory(id,dto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
