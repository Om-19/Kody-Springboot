package com.task.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.task.enums.OfferType;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OfferRequestDto {

    @NotBlank(message = "Offer name is required")
    private String offerName;

    @NotNull(message = "Offer type is required")
    private OfferType offerType;

    @NotNull(message = "Offer value is required")
    @Positive(message = "Offer value must be positive")
    private BigDecimal offerValue;

    @NotNull(message = "Max usage per customer is required")
    @Positive(message = "Max usage per customer must be greater than 0")
    private Integer maxUsagePerCustomer;

    @NotNull(message = "Max discount value is required")
    @Positive(message = "Max discount value must be positive")
    private BigDecimal maxDiscountValue;

    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDateTime endDate;
}
