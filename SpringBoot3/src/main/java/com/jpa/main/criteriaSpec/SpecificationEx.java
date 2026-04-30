package com.jpa.main.criteriaSpec;

import org.springframework.data.jpa.domain.Specification;

import com.jpa.main.entity.BloodGroup;
import com.jpa.main.entity.Patient;

public class SpecificationEx {
    public static Specification<Patient> hasName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name + "%");
    }

    public static Specification<Patient> hasBloodGroup(BloodGroup bloodGroup) {
        return (root, query, cb) -> cb.equal(root.get("bloodGroup"), bloodGroup);
    }
}
