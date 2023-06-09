package com.mypractice.restaurantmgt.controller;

import com.mypractice.restaurantmgt.dto.RestaurantDto;
import com.mypractice.restaurantmgt.dto.RestaurantResponseDto;
import com.mypractice.restaurantmgt.handler.NotificationHandler;
import com.mypractice.restaurantmgt.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final NotificationHandler notificationHandler;

    @PostMapping
    public ResponseEntity<RestaurantDto> addRestaurant(@RequestBody RestaurantDto dto) throws IOException {
        RestaurantDto restaurantDto = restaurantService.addRestaurant(dto);
        notificationHandler.sendNotification(String.format("new restaurant %s has regsiter successfully with license number %s", restaurantDto.getName(), restaurantDto.getLicenseDto().getLicenseNumber()));
        return new ResponseEntity<>(restaurantDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> findAllRestaurant() {
        List<RestaurantResponseDto> restaurantDtos = restaurantService.findAllRestaurant();
        return ResponseEntity.ok(restaurantDtos);
    }

    @GetMapping("/{restaurant-id}")
    public ResponseEntity<RestaurantResponseDto> findRestaurantById(@PathVariable("restaurant-id") Long restaurantId) {
        RestaurantResponseDto restaurantDto = restaurantService.findRestaurantById(restaurantId);
        return ResponseEntity.ok(restaurantDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantDto>> findAllRestaurantWithDetails() {
        List<RestaurantDto> restaurantDtos = restaurantService.findAllRestaurantWithDetails();
        return ResponseEntity.ok(restaurantDtos);
    }
}
