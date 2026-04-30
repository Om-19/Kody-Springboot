package com.jpa.main.dto;

import com.jpa.main.entity.BloodGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PatientSearchCriteria {
    private String name;
    private String city; // (if later added)
    private BloodGroup bloodGroup;
    private Integer minAge;
    private Integer maxAge;
    private String doctorName;
    private String specialization;

}