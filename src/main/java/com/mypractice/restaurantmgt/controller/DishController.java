package com.mypractice.restaurantmgt.controller;

import com.mypractice.restaurantmgt.dto.DishDto;
import com.mypractice.restaurantmgt.entity.Restaurant;
import com.mypractice.restaurantmgt.service.DishService;
import com.mypractice.restaurantmgt.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;
    private final RestaurantService restaurantService;

    @PostMapping("/{restaurant-id}")
    public ResponseEntity<List<DishDto>> createDishes(@RequestBody List<DishDto> dishDtos, @PathVariable("restaurant-id") Long restaurantId) {
        List<DishDto> dishes = dishService.addDishes(dishDtos, restaurantService.findRestaurantByRestaurantId(restaurantId));
        return new ResponseEntity<>(dishes, HttpStatus.CREATED);
    }

    @GetMapping("/{restaurant-id}")
    public ResponseEntity<List<DishDto>> findAllDishes(@PathVariable("restaurant-id") Long restaurantId){
        Restaurant restaurant = restaurantService.findRestaurantByRestaurantId(restaurantId);
        return  ResponseEntity.ok(dishService.findAllDishesByRestaurant(restaurant));
    }
    @GetMapping("/{dish-id}/dish")
    public ResponseEntity<DishDto> findDishByDishId(@PathVariable("dish-id") Long dishId){
        return  ResponseEntity.ok(dishService.findDishByDishId(dishId));
    }

    @PutMapping
    public ResponseEntity<DishDto> updateDishes( @RequestBody DishDto dishDto){
        return  ResponseEntity.ok(dishService.updateDishes(dishDto));
    }

    @PutMapping("/{restaurant-id}")
    public ResponseEntity<List<DishDto>> updateDishesByRestaurant(@PathVariable("restaurant-id") Long restaurantId , @RequestBody List<DishDto> dishDto){
        Restaurant restaurant = restaurantService.findRestaurantByRestaurantId(restaurantId);
        return  ResponseEntity.ok(dishService.addDishes(dishDto, restaurant));
    }

}
