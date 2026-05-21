package com.task.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productOrderId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id")
    private CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    private BigDecimal finalPrice;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
    }
}