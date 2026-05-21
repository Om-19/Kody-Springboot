package com.task.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.task.enums.OrderStatus;
import com.task.enums.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String customerName;

    @Column(nullable = false)
    private String customerEmail;

    private String customerPhone;

    @Column(columnDefinition = "TEXT")
    private String shippingAddress;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    private BigDecimal discountAmount;

    private BigDecimal finalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @JsonManagedReference
    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrder> productOrders;

    @PrePersist
    public void prePersist() {

        orderDate = LocalDateTime.now();

        if (orderStatus == null) {
            orderStatus = OrderStatus.PLACED;
        }

        if (paymentStatus == null) {
            paymentStatus = PaymentStatus.PENDING;
        }
    }
}