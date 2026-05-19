package com.task.exception.customExc;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {

    private LocalDateTime timestamp;

    private Integer status;

    private String error;

    private Map<String, String> messages;
}