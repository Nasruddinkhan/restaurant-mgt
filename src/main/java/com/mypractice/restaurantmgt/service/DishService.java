package com.mypractice.restaurantmgt.service;

import com.mypractice.restaurantmgt.dto.DishDto;
import com.mypractice.restaurantmgt.entity.Restaurant;

import java.util.List;

public interface DishService {
    DishDto addDishes(List<DishDto> dishDtos);
    List<DishDto> addDishes(List<DishDto> dishDtos, Restaurant restaurant);
}
