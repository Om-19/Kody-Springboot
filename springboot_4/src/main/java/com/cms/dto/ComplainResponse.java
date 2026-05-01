package com.cms.dto;

import java.time.LocalDateTime;

import com.cms.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ComplainResponse {
    private Long id;
    private String title;
    private Status status;
    private LocalDateTime createdAt;
}
