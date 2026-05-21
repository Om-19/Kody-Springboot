package com.task.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDTO {

    private Long productOrderId;

    private Long productId;

    private String productName;

    private String productCode;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    private BigDecimal finalPrice;
}