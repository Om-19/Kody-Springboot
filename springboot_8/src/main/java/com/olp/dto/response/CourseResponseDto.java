package com.olp.dto.response;

import com.olp.entity.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {

    private Long id;

    private String name;

    private String subjectCode;

    private String description;

    private Category category;

    private String instructorName;

    private String instructorEmail;

    private String institutionName;
}