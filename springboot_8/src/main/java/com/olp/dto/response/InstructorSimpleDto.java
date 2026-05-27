package com.olp.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstructorSimpleDto {

    private Long id;

    private String name;

    private String email;
}