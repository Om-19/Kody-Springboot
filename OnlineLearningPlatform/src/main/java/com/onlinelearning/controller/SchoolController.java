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

import com.onlinelearning.dto.schoolDTOs.SchoolRequestDTO;
import com.onlinelearning.dto.schoolDTOs.SchoolResponceDTO;
import com.onlinelearning.service.SchoolService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/school")
public class SchoolController {
	@Autowired
	private SchoolService schoolService;
	
	@PostMapping
	public ResponseEntity<SchoolResponceDTO> saveCategory(@Valid @RequestBody SchoolRequestDTO dto){
		
		return new ResponseEntity<SchoolResponceDTO>(schoolService.addSchool(dto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<SchoolResponceDTO>> getAllCategories(){
		List<SchoolResponceDTO> dtos = schoolService.getAllSchools();
		if(dtos.size() > 0) {
			return new ResponseEntity<List<SchoolResponceDTO>>(dtos,HttpStatus.FOUND);
		}
		
		return new ResponseEntity<List<SchoolResponceDTO>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SchoolResponceDTO> getCategoryById(@PathVariable long id){
		
		return new ResponseEntity<SchoolResponceDTO>(schoolService.getSchoolById(id),HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SchoolResponceDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody SchoolRequestDTO dto){
		
		return new ResponseEntity<SchoolResponceDTO>(schoolService.updateSchool(id,dto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		schoolService.deleteSchool(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
