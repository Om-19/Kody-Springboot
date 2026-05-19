package com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.entity.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

}
