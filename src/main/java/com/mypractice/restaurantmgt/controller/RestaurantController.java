package com.mypractice.restaurantmgt.controller;

import com.mypractice.restaurantmgt.dto.RestaurantDto;
import com.mypractice.restaurantmgt.dto.RestaurantResponseDto;
import com.mypractice.restaurantmgt.handler.NotificationHandler;
import com.mypractice.restaurantmgt.mapper.EmailMapper;
import com.mypractice.restaurantmgt.service.MailSenderService;
import com.mypractice.restaurantmgt.service.RestaurantService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final MailSenderService mailSenderService;

    @Value("${restaurant.registration-template-name}")
    private String registrationTemplate;
    private final EmailMapper emailMapper;

    @PostMapping
    public ResponseEntity<RestaurantDto> addRestaurant(@RequestBody RestaurantDto dto) throws IOException, MessagingException {
        RestaurantDto restaurantDto = restaurantService.addRestaurant(dto);
        notificationHandler.sendNotification(String.format("new restaurant %s has register successfully with license number %s", restaurantDto.getName(), restaurantDto.getLicenseDto().getLicenseNumber()));
        mailSenderService.sendEmail(emailMapper.getEmailDto(dto), registrationTemplate);
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

    @DeleteMapping("/{restaurant-id}")
    public ResponseEntity<Void> blockTheRestaurant(@PathVariable("restaurant-id") Long restaurantId) {
        restaurantService.blockTheRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }




}
