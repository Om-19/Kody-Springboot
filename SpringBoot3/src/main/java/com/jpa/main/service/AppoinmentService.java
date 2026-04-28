package com.jpa.main.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.main.entity.Appoinment;
import com.jpa.main.entity.Doctor;
import com.jpa.main.entity.Patient;
import com.jpa.main.repository.AppoinmentRepository;
import com.jpa.main.repository.DoctorRepository;
import com.jpa.main.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppoinmentService {

    private final AppoinmentRepository appoinmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    /*
     * No need for cascading as we dont want to create/delete any new Patient/Doctor
     * Also Appoinment is manyToOne So generally this relationship has no need for
     * cascading
     * But cascade will be required in Patient on list(appoinment) so that if
     * patient
     * gets removed
     * all appoinments hsould be deleted.
     */
    @Transactional
    public Appoinment createAppoinment(Appoinment appoinment, Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if (appoinment.getId() != null)
            throw new IllegalArgumentException("Appoinment Should not have Id");

        appoinment.setPatient(patient);
        appoinment.setDoctor(doctor);

        patient.getAppoinment().add(appoinment); // to maintain consistency

        return appoinmentRepository.save(appoinment);
    }

    @Transactional
    public Appoinment assignAppoinmentToAnotherDoctor(Long appoinmentId, Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Appoinment appoinment = appoinmentRepository.findById(appoinmentId).orElseThrow();

        appoinment.setDoctor(doctor); // this will automatically the update as appoinment will get dirty
        doctor.getAppoinmentList().add(appoinment); // // for bi-directional consistency

        return appoinment;
    }
}
