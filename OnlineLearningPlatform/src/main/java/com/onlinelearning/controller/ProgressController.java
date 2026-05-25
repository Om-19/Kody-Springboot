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

import com.onlinelearning.dto.progressDTOs.ProgresRequestDTO;
import com.onlinelearning.dto.progressDTOs.ProgresResponceDTO;
import com.onlinelearning.service.ProgressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/progress")
public class ProgressController {
	@Autowired
	private ProgressService progressService;
	
	@PostMapping
	public ResponseEntity<ProgresResponceDTO> saveCategory(@Valid @RequestBody ProgresRequestDTO dto){
		
		return new ResponseEntity<ProgresResponceDTO>(progressService.addProgress(dto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ProgresResponceDTO>> getAllCategories(){
		List<ProgresResponceDTO> dtos = progressService.getAllProgress();
		if(dtos.size() > 0) {
			return new ResponseEntity<List<ProgresResponceDTO>>(dtos,HttpStatus.FOUND);
		}
		
		return new ResponseEntity<List<ProgresResponceDTO>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProgresResponceDTO> getCategoryById(@PathVariable long id){
		
		return new ResponseEntity<ProgresResponceDTO>(progressService.getProgressById(id),HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProgresResponceDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody ProgresRequestDTO dto){
		
		return new ResponseEntity<ProgresResponceDTO>(progressService.updateProgress(id,dto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		progressService.deleteProgress(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
