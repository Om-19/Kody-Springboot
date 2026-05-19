package com.task.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Productresponse {

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Product code is required")
    private String productCode;

    @NotBlank(message = "Brand name is required")
    private String brandName;

    @Positive(message = "Price must Be positive.")
    private Double price;

    @PositiveOrZero(message = "Stock quantity must be a positive number or zero.")
    private Integer stockQuantity;

    @NotBlank(message = "Product Description is required")
    private String productDescription;
}
