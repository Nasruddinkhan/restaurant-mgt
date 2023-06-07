package com.mypractice.restaurantmgt.controller;

import com.mypractice.restaurantmgt.dto.RestaurantDto;
import com.mypractice.restaurantmgt.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;


    @PostMapping
    public ResponseEntity<RestaurantDto> addRestaurant(@RequestBody RestaurantDto dto) {
        RestaurantDto restaurantDto = restaurantService.addRestaurant(dto);
        return new ResponseEntity<>(restaurantDto, HttpStatus.CREATED);
    }

}
