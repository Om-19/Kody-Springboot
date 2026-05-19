package com.task.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Productresponse {
    private Long productId;
    private String productName;
    private String productCode;
    private String brandName;
    private Double price;
    private Integer stockQuantity;
    private String productDescription;
    private String productImage;
    private boolean active;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
