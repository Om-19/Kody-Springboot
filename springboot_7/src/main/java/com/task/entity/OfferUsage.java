package com.task.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
// one email + one offer combination exists only once.
@Table(name = "offer_usage", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "customer_email", "offer_id" })
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerUsageId;

    @Column(nullable = false)
    private String customerEmail;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @Column(nullable = false)
    private Integer usedCount;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    // Runs automatically:
    // BEFORE entity is inserted into DB.
    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();

        if (usedCount == null) {
            usedCount = 1;
        }
    }

    // Runs automatically:
    // BEFORE UPDATE query executes.
    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();
    }
}