package com.mypractice.restaurantmgt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishDto {
    private Long dishId;
    private String name;
    private String description;
    private BigDecimal price;
}
