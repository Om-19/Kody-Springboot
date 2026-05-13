package com.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    /*
    We don't expose raw exception objects directly to clients.
    */

    private String message;

    private int status;

    private LocalDateTime timestamp;
}