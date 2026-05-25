package com.olp.dto.request;

import com.olp.entity.Institution;
import com.olp.entity.Instructor;
import com.olp.entity.enums.Category;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @NotNull
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @NotBlank
    private Instructor instructor;

    private Institution institution;
}
