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

import com.onlinelearning.dto.enrollmentDTOs.EnrollmentRequestDTO;
import com.onlinelearning.dto.enrollmentDTOs.EnrollmentResponceDTO;
import com.onlinelearning.service.EnrollmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
	@Autowired
	private EnrollmentService enrollmentService;
	
	@PostMapping
	public ResponseEntity<EnrollmentResponceDTO> saveCategory(@Valid @RequestBody EnrollmentRequestDTO dto){
		
		return new ResponseEntity<EnrollmentResponceDTO>(enrollmentService.addEnrollment(dto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<EnrollmentResponceDTO>> getAllCategories(){
		List<EnrollmentResponceDTO> dtos = enrollmentService.getAllEnrollments();
		if(dtos.size() > 0) {
			return new ResponseEntity<List<EnrollmentResponceDTO>>(dtos,HttpStatus.FOUND);
		}
		
		return new ResponseEntity<List<EnrollmentResponceDTO>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EnrollmentResponceDTO> getCategoryById(@PathVariable long id){
		
		return new ResponseEntity<EnrollmentResponceDTO>(enrollmentService.getEnrollmentById(id),HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EnrollmentResponceDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody EnrollmentRequestDTO dto){
		
		return new ResponseEntity<EnrollmentResponceDTO>(enrollmentService.updateEnrollment(id,dto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		enrollmentService.deleteEnrollment(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
