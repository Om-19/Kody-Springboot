package com.task.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class OrderRequestDTO {

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Customer email is required")
    @Email(message = "Invalid email format")
    private String customerEmail;

    @NotBlank(message = "Customer phone is required")
    private String customerPhone;

    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;

    // Optional
    private Long offerId;

    @NotEmpty(message = "Order must contain at least one product") // Dont use not null bcs it allows "products" = []
    @Valid // without this nested DTO validations WON’T run.
    private List<OrderItemRequestDTO> products;
}