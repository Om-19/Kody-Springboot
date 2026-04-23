package com.jpa.main.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BloodgroupCount {
    private BloodGroup bloodGroup;
    private Long count;
}
