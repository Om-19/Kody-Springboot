package com.jpa.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.main.criteriaSpec.PatientCriteriaQuery;
import com.jpa.main.dto.PatientSearchCriteria;
import com.jpa.main.entity.Patient;

@Service
public class CriteriaService {

    @Autowired
    PatientCriteriaQuery pq;

    public List<Patient> getPatientFromCriteria(PatientSearchCriteria sc) {
        return pq.findPatientsBasics(sc);
    }
}
