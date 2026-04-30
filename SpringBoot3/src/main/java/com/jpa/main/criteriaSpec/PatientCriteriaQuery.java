package com.jpa.main.criteriaSpec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jpa.main.dto.PatientSearchCriteria;
import com.jpa.main.entity.Patient;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class PatientCriteriaQuery {

    @PersistenceContext
    private EntityManager em;

    /*
     * SELECT * FROM patient
     * WHERE name LIKE '%xyz%'
     * AND blood_group = 'A_POSITIVE';
     */
    public List<Patient> findPatientsBasics(PatientSearchCriteria sc) {
        CriteriaBuilder cb = em.getCriteriaBuilder(); // tools to build SQL queries programmatically
        CriteriaQuery<Patient> cq = cb.createQuery(Patient.class); // query that returns Patient objects
        Root<Patient> root = cq.from(Patient.class); // reference to table

        List<Predicate> predicates = new ArrayList<>(); // condition (WHERE clause)

        // Add condition: Name (if exists)
        if (sc.getName() != null) {
            predicates.add(cb.like(root.get("name"), "%" + sc.getName() + "%")); // Important: % = wildcard dynamic →
                                                                                 // only added if input exists
        }

        if (sc.getBloodGroup() != null) {
            predicates.add(cb.equal(root.get("bloodGroup"), sc.getBloodGroup()));
        }

        // Apply WHERE clause
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        return em.createQuery(cq).getResultList();
    }
}
