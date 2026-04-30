package com.jpa.main.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jpa.main.criteriaSpec.SpecificationEx;
import com.jpa.main.dto.PatientSearchCriteria;
import com.jpa.main.entity.Patient;
import com.jpa.main.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpecificationService {
    private final PatientRepository patientRepository;

    public List<Patient> searchPatients(PatientSearchCriteria sc) {
        Specification<Patient> spec = (root, query, cb) -> cb.conjunction();

        if (sc.getName() != null && !sc.getName().isBlank()) {
            spec = spec.and(SpecificationEx.hasName(sc.getName()));
        }

        if (sc.getBloodGroup() != null) {
            spec = spec.and(SpecificationEx.hasBloodGroup(sc.getBloodGroup()));
        }

        return patientRepository.findAll(spec);
    }
}
