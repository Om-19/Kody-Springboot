package com.jpa.main.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.main.entity.Patient;
import com.jpa.main.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    PatientRepository repo;

    public List<Patient> findByBirthDateBetween(LocalDate of, LocalDate of2) {
        List<Patient> ls = repo.findByBirthDateBetween(of, of2);

        System.out.println();
        for (Patient p : ls) {
            System.out.println(p.getName());
        }

        return ls;
    }

}
