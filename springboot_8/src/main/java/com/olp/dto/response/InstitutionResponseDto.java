package com.olp.dto.response;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstitutionResponseDto {

    private Long id;

    private String name;

    private String website;

    private Set<InstructorSimpleDto> instructors;
}