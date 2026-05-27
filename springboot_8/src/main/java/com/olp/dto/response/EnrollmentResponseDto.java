package com.olp.dto.response;

import java.time.LocalDateTime;

import com.olp.entity.enums.EnrollmentStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class EnrollmentResponseDto {

    private Long enrollmentId;

    private String studentName;

    private String courseName;

    private EnrollmentStatus status;

    private LocalDateTime enrolledAt;
}
