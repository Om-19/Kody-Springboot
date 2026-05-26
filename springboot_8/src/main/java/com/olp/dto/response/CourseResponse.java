package com.olp.dto.response;

import com.olp.entity.enums.Category;

import lombok.Data;

@Data
public class CourseResponse {
    private String name;

    private String description;

    private Category category;

    private Long institutionId;
}
