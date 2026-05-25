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

import com.onlinelearning.dto.courseDTOs.CourseRequestDTO;
import com.onlinelearning.dto.courseDTOs.CourseResponceDTO;
import com.onlinelearning.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@PostMapping
	public ResponseEntity<CourseResponceDTO> saveCategory(@Valid @RequestBody CourseRequestDTO dto){
		
		return new ResponseEntity<CourseResponceDTO>(courseService.saveCourse(dto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CourseResponceDTO>> getAllCategories(){
		List<CourseResponceDTO> dtos = courseService.getAllCourses();
		if(dtos.size() > 0) {
			return new ResponseEntity<List<CourseResponceDTO>>(dtos,HttpStatus.FOUND);
		}
		
		return new ResponseEntity<List<CourseResponceDTO>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CourseResponceDTO> getCategoryById(@PathVariable long id){
		
		return new ResponseEntity<CourseResponceDTO>(courseService.findCourseById(id),HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CourseResponceDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CourseRequestDTO dto){
		
		return new ResponseEntity<CourseResponceDTO>(courseService.updateCourse(id,dto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		courseService.deleteCourse(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
