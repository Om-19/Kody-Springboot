package com.task.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.task.enums.OfferType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponseDto {
    private Long offerId;

    private String offerName;

    private OfferType offerType;

    private BigDecimal offerValue;

    private Integer maxUsagePerCustomer;

    private BigDecimal maxDiscountValue;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean active;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
