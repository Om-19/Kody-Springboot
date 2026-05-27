package com.olp.dto.request;

import com.olp.entity.enums.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseDto {

    @NotBlank
    private String name;

    @NotBlank
    private String subjectCode;

    @NotBlank
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @NotNull
    private Category category;

    // @NotNull
    private Long instructorId;

    // optional because course can be independent
    private Long institutionId;
}
