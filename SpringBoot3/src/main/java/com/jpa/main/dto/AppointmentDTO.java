package com.jpa.main.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

    private Long id;
    private String reason;
    private LocalDateTime appointmentDate;

    private String patientName;
    private String doctorName;
    private String departmentName;
}
