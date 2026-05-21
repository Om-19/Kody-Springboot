package com.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.entity.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    List<CustomerOrder> findByCustomerEmailIgnoreCase(String email);
}
