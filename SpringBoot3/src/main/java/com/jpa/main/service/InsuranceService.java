package com.jpa.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.main.entity.Insurance;
import com.jpa.main.entity.Patient;
import com.jpa.main.repository.InsuranceRepository;
import com.jpa.main.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient Not Found with ID: " + patientId));

        patient.setInsurance(insurance);
        insurance.setPatient(patient);

        return patient;
    }
}
