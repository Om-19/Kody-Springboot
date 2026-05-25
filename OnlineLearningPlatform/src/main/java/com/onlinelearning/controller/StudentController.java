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

import com.onlinelearning.dto.studentDTOs.StudentRequestDTO;
import com.onlinelearning.dto.studentDTOs.StudentResponceDTO;
import com.onlinelearning.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@PostMapping
	public ResponseEntity<StudentResponceDTO> saveCategory(@Valid @RequestBody StudentRequestDTO dto){
		
		return new ResponseEntity<StudentResponceDTO>(studentService.addStudent(dto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<StudentResponceDTO>> getAllCategories(){
		List<StudentResponceDTO> dtos = studentService.getAllStudents();
		if(dtos.size() > 0) {
			return new ResponseEntity<List<StudentResponceDTO>>(dtos,HttpStatus.FOUND);
		}
		
		return new ResponseEntity<List<StudentResponceDTO>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentResponceDTO> getCategoryById(@PathVariable long id){
		
		return new ResponseEntity<StudentResponceDTO>(studentService.getStudentById(id),HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StudentResponceDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody StudentRequestDTO dto){
		
		return new ResponseEntity<StudentResponceDTO>(studentService.updateStudent(id,dto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
