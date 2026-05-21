package com.task.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.task.enums.OrderStatus;
import com.task.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private Long orderId;

    private String customerName;

    private String customerEmail;

    private String customerPhone;

    private String shippingAddress;

    private LocalDateTime orderDate;

    private OrderStatus orderStatus;

    private PaymentStatus paymentStatus;

    private BigDecimal totalAmount;

    private BigDecimal discountAmount;

    private BigDecimal finalAmount;

    private Long offerId;

    private String offerName;

    private List<OrderItemResponseDTO> products;
}