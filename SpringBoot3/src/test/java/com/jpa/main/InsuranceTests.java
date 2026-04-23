package com.jpa.main;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa.main.entity.Insurance;
import com.jpa.main.entity.Patient;
import com.jpa.main.service.InsuranceService;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

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
}
