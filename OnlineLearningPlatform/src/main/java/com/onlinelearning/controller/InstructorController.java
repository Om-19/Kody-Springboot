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

import com.onlinelearning.dto.instructorDTOs.InstructorRequestDTO;
import com.onlinelearning.dto.instructorDTOs.InstructorResponceDTO;
import com.onlinelearning.service.InstructorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
	@Autowired
	private InstructorService instructorService;
	
	@PostMapping
	public ResponseEntity<InstructorResponceDTO> saveCategory(@Valid @RequestBody InstructorRequestDTO dto){
		
		return new ResponseEntity<InstructorResponceDTO>(instructorService.addInstructor(dto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<InstructorResponceDTO>> getAllCategories(){
		List<InstructorResponceDTO> dtos = instructorService.getAllInstructors();
		if(dtos.size() > 0) {
			return new ResponseEntity<List<InstructorResponceDTO>>(dtos,HttpStatus.FOUND);
		}
		
		return new ResponseEntity<List<InstructorResponceDTO>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<InstructorResponceDTO> getCategoryById(@PathVariable long id){
		
		return new ResponseEntity<InstructorResponceDTO>(instructorService.getInstructorById(id),HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<InstructorResponceDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody InstructorRequestDTO dto){
		
		return new ResponseEntity<InstructorResponceDTO>(instructorService.updateInstructor(id,dto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		instructorService.deleteInstructor(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
