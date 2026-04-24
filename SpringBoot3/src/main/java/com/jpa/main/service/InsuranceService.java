package com.jpa.main.service;

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

    /*
     * Transactional will only work when we haven given cascade in in parent class
     * to its child
     * then only it will save insurance first -> patient will add insurance _-
     * patient in dirty state
     * & at end of method all new changes will be committed in db.
     */
    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient Not Found with ID: " + patientId));

        patient.setInsurance(insurance);
        insurance.setPatient(patient);

        return patient;
    }
}
