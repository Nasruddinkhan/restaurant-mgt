package com.mypractice.restaurantmgt.mapper;

import com.mypractice.restaurantmgt.dto.DishDto;
import com.mypractice.restaurantmgt.entity.Dish;
import com.mypractice.restaurantmgt.entity.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DishMapper {
    public  Dish convertDishDtoToDish(DishDto dishDto, Restaurant restaurant){
        return Dish.builder()
                .isActive(true)
                .dishId(dishDto.getDishId())
                .name(dishDto.getName())
                .description(dishDto.getDescription())
                .price(dishDto.getPrice())
                .restaurant(restaurant)
                .build();
    }

    public DishDto convertDishToDishDto(Dish dish) {
        return DishDto.builder()
                .dishId(dish.getDishId())
                .name(dish.getName())
                .price(dish.getPrice())
                .description(dish.getDescription())
                .build();
    }
}
