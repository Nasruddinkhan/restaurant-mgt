package com.mypractice.restaurantmgt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantResponseDto {

    private Long restaurantId;
    private String name;
    private String email;

}
