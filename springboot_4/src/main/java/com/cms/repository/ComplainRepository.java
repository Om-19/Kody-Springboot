package com.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.entity.Complain;

public interface ComplainRepository extends JpaRepository<Complain, Long> {

}
