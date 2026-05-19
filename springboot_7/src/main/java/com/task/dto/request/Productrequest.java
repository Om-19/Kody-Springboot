package com.task.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

// @Data internally includes:
// @Getter
// @Setter
// @ToString
// @EqualsAndHashCode
// @RequiredArgsConstructor
@Data
public class Productrequest {

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Product code is required")
    @Size(max = 8, message = "Product code must be at most 8 characters")
    private String productCode;

    @NotBlank(message = "Brand name is required")
    private String brandName;

    @Positive(message = "Price must be positive.")
    private Double price;

    @PositiveOrZero(message = "Stock quantity must be a positive number or zero.")
    private Integer stockQuantity;

    private String productDescription;

    private String productImage;
}
