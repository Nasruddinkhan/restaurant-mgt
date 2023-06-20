package com.mypractice.restaurantmgt.service;

import com.mypractice.restaurantmgt.dto.RestaurantDto;
import com.mypractice.restaurantmgt.dto.RestaurantResponseDto;
import com.mypractice.restaurantmgt.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    RestaurantDto addRestaurant(RestaurantDto dto);

    List<RestaurantResponseDto> findAllRestaurant();

    RestaurantResponseDto findRestaurantById(Long restaurantId);
    Restaurant findRestaurantByRestaurantId(Long restaurantId);

    List<RestaurantDto> findAllRestaurantWithDetails();

    void blockTheRestaurant(Long restaurantId);
}
