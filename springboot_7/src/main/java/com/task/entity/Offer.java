package com.task.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.task.enums.OfferType;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    @Column(nullable = false)
    private String offerName;

    @Enumerated(EnumType.STRING)
    private OfferType offerType;

    @Column(nullable = false)
    private BigDecimal offerValue;

    private Integer maxUsagePerCustomer;

    private BigDecimal maxDiscountValue;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean active = true;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();
    }
}