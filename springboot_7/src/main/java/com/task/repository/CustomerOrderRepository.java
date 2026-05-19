package com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.entity.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

}
