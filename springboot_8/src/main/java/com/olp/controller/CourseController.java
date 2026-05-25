package com.olp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olp.dto.request.CourseDto;
import com.olp.service.CourseServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/instructor/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseServiceImpl courseServiceImpl;

    @PostMapping("/create")
    public String createCourse(@RequestBody CourseDto entity) {
        return courseServiceImpl.saveCourse(entity);
    }

}
