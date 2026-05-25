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

import com.onlinelearning.dto.storageDTOs.StorageRequestDTO;
import com.onlinelearning.dto.storageDTOs.StorageResponceDTO;
import com.onlinelearning.service.StorageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/storage")
public class StorageController {
	@Autowired
	private StorageService storageService;
	
	@PostMapping
	public ResponseEntity<StorageResponceDTO> saveCategory(@Valid @RequestBody StorageRequestDTO dto){
		
		return new ResponseEntity<StorageResponceDTO>(storageService.addStorage(dto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<StorageResponceDTO>> getAllCategories(){
		List<StorageResponceDTO> dtos = storageService.getAllStorageFiles();
		if(dtos.size() > 0) {
			return new ResponseEntity<List<StorageResponceDTO>>(dtos,HttpStatus.FOUND);
		}
		
		return new ResponseEntity<List<StorageResponceDTO>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StorageResponceDTO> getCategoryById(@PathVariable long id){
		
		return new ResponseEntity<StorageResponceDTO>(storageService.getStorageById(id),HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StorageResponceDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody StorageRequestDTO dto){
		
		return new ResponseEntity<StorageResponceDTO>(storageService.updateStorage(id,dto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		storageService.deleteStorage(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
