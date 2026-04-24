package com.jpa.main;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa.main.entity.Appoinment;
import com.jpa.main.entity.Insurance;
import com.jpa.main.entity.Patient;
import com.jpa.main.service.InsuranceService;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    /*
     * The Builder method in Java refers to a creational design pattern used to
     * construct complex objects
     * step-by-step. It is particularly useful for classes with many attributes,
     * especially when some are optional,
     * as it avoids "telescoping constructors" (long lists of parameters)
     */
    @Test
    public void testInsurance() {
        Insurance insurance = Insurance.builder()
                .policyNumber("SBI_1234")
                .provider("SBI")
                .validUntil(LocalDate.of(2030, 12, 12))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 3L);
        System.out.println(patient.toString());
    }

    @Test
    public void testAppoinment(){
        Appoinment appoinment = Appoinment.builder()
        .appoinmentDate(LocalDateTime.of(2025, 03, 03, 12, 12, 12))
        .patient()
    }
}
