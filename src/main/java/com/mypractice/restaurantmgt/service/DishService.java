package com.mypractice.restaurantmgt.service;

import com.mypractice.restaurantmgt.dto.DishDto;
import com.mypractice.restaurantmgt.entity.Restaurant;

import java.util.List;

public interface DishService {
    List<DishDto> addDishes(List<DishDto> dishDtos, Restaurant restaurant);

    List<DishDto> findAllDishesByRestaurant(Restaurant restaurant);

    DishDto findDishByDishId(Long dishId);

    DishDto updateDishes(DishDto dishDto);

    Restaurant blockTheDishes(Restaurant restaurant);
}
