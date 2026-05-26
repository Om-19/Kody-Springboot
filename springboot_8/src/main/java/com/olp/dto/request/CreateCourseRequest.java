package com.olp.dto.request;

import com.olp.entity.enums.Category;

import lombok.Data;

@Data

public class CreateCourseRequest {

    private String name;

    private String description;

    private Category category;

    private Long institutionId;
}