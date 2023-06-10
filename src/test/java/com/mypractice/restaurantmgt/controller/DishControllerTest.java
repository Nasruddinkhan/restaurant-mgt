package com.mypractice.restaurantmgt.controller;

import com.mypractice.restaurantmgt.dto.DishDto;
import com.mypractice.restaurantmgt.dto.RestaurantDto;
import com.mypractice.restaurantmgt.service.RestaurantService;
import com.mypractice.restaurantmgt.util.CommonUtilTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class DishControllerTest {
    @LocalServerPort
    private int port;
    private final TestRestTemplate restTemplate;
    private final RestaurantService restaurantService;
    HttpHeaders headers;
    private List<DishDto> dishDto;

    @Autowired
    DishControllerTest(TestRestTemplate restTemplate, RestaurantService restaurantService) {
        this.restTemplate = restTemplate;
        this.restaurantService = restaurantService;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dishDto = new ArrayList<>();
        dishDto = CommonUtilTest.loadFile("json/dishes-payload.json", dishDto, getClass().getClassLoader());
        RestaurantDto restaurantDto = CommonUtilTest.loadFile("json/restaurant-payload.json", RestaurantDto.class, getClass().getClassLoader());
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)); // Add Accept header
        restaurantService.addRestaurant(restaurantDto);
    }

    @Test
    void createDishes() {
        HttpEntity<List<DishDto>> requestEntity = new HttpEntity<>(dishDto, headers);
        final URI targetUrl = fromUriString("http://localhost:" + port + "/restaurant-svc/api/v1/dishes/1")
                .build()
                .encode()
                .toUri();
        ResponseEntity<List<DishDto>> response = restTemplate.exchange(
                targetUrl,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<DishDto>>() {
                });
        List<DishDto> dishDtos = response.getBody();
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(dishDtos);


    }

    @Test
    void findAllDishes() {
        final URI targetUrl = fromUriString("http://localhost:" + port + "/restaurant-svc/api/v1/dishes/1")
                .build()
                .encode()
                .toUri();
        ResponseEntity<List<DishDto>> response = restTemplate.exchange(
                targetUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DishDto>>() {
                });
        List<DishDto> dishDtos = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(dishDtos);
    }

    @Test
    void findDishByDishId() {
        final URI targetUrl = fromUriString("http://localhost:" + port + "/restaurant-svc/api/v1/dishes/1/dish")
                .build()
                .encode()
                .toUri();
        ResponseEntity<DishDto> response = restTemplate.exchange(
                targetUrl,
                HttpMethod.GET,
                null,
                DishDto.class);
        DishDto dishDtos = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(dishDtos);
    }

    @Test
    void updateDishes() {

        HttpEntity<String> requestEntity = new HttpEntity<>("{\n" +
                "    \"dishId\":1,\n" +
                "    \"name\": \"Beef Bihari Seek\",\n" +
                "    \"description\": \"Beef Bihari Seek\",\n" +
                "    \"price\": \"80.00\"\n" +
                "  }", headers);
        final URI targetUrl = fromUriString("http://localhost:" + port + "/restaurant-svc/api/v1/dishes")
                .build()
                .encode()
                .toUri();
        ResponseEntity<DishDto> response = restTemplate.exchange(
                targetUrl,
                HttpMethod.PUT,
                requestEntity,
                DishDto.class
                );
        DishDto dishDtos = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(dishDtos);
    }

    @Test
    void updateDishesByRestaurant() {

        HttpEntity<List<DishDto>> requestEntity = new HttpEntity<>(dishDto, headers);
        final URI targetUrl = fromUriString("http://localhost:" + port + "/restaurant-svc/api/v1/dishes/1")
                .build()
                .encode()
                .toUri();
        ResponseEntity<List<DishDto>> response = restTemplate.exchange(
                targetUrl,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<List<DishDto>>() {
                });
        List<DishDto> dishDtos = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(dishDtos);

    }
}