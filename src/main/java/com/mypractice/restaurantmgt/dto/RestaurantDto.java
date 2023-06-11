package com.mypractice.restaurantmgt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto {

    private Long restaurantId;
    private String name;
    private List<DishDto> dishDto;
    private LicenseDto licenseDto;
    private String email;

}
