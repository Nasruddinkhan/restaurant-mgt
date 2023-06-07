package com.mypractice.restaurantmgt.mapper;

import com.mypractice.restaurantmgt.dto.DishDto;
import com.mypractice.restaurantmgt.dto.LicenseDto;
import com.mypractice.restaurantmgt.dto.RestaurantDto;
import com.mypractice.restaurantmgt.entity.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RestaurantMapper {
    public  Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto){
       return Restaurant.builder()
                .restaurantId(restaurantDto.getRestaurantId())
                .name(restaurantDto.getName())
                .build();
    }

    public RestaurantDto restaurantToRestaurantDto(Restaurant restaurant, List<DishDto> dishes, LicenseDto licences) {
        return RestaurantDto.builder()
                .restaurantId(restaurant.getRestaurantId())
                .name(restaurant.getName())
                .dishDto(dishes)
                .licenseDto(licences)
                .build();
    }
}
