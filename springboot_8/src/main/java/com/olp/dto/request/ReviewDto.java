package com.olp.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    @Min(1)
    @Max(5)
    private int rating;

    // @NotNull
    @Size(min = 10, max = 500, message = "Comment must be between 10 and 500 characters")
    private String comment;

    private Long courseId;
}
