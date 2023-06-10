package com.mypractice.restaurantmgt.controller;

import com.mypractice.restaurantmgt.dto.LicenseDto;
import com.mypractice.restaurantmgt.dto.RestaurantDto;
import com.mypractice.restaurantmgt.entity.Restaurant;
import com.mypractice.restaurantmgt.repository.RestaurantRepository;
import com.mypractice.restaurantmgt.service.LicenseService;
import com.mypractice.restaurantmgt.service.RestaurantService;
import com.mypractice.restaurantmgt.util.CommonUtilTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class LicenseControllerTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate;
    private final RestaurantService restaurantService;
    LicenseDto licenseDto;
    HttpHeaders headers;

    @Autowired
    LicenseControllerTest(TestRestTemplate restTemplate, RestaurantService restaurantService) {
        this.restTemplate = restTemplate;
        this.restaurantService = restaurantService;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        licenseDto = CommonUtilTest.loadFile("json/license-payload.json", LicenseDto.class, getClass().getClassLoader());
        RestaurantDto restaurantDto = CommonUtilTest.loadFile("json/restaurant-payload.json", RestaurantDto.class, getClass().getClassLoader());
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)); // Add Accept header
        restaurantService.addRestaurant(restaurantDto);
    }

    @Test
    void updateLicense() {
        HttpEntity<LicenseDto> requestEntity = new HttpEntity<>(licenseDto, headers);
        final URI targetUrl = fromUriString("http://localhost:" + port + "/restaurant-svc/api/v1/license/1")
                .build()
                .encode()
                .toUri();
        ResponseEntity<LicenseDto> response =
                restTemplate.exchange(
                        targetUrl,
                        HttpMethod.PUT,
                        requestEntity,
                        LicenseDto.class);
        LicenseDto res = response.getBody();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, res.getLicenseNo());
        assertEquals(licenseDto.getType(), res.getType());
    }

    @Test
    void findLicenseByLicenseId() {
        final URI targetUrl = fromUriString("http://localhost:" + port + "/restaurant-svc/api/v1/license/1")
                .build()
                .encode()
                .toUri();
        ResponseEntity<LicenseDto> response =
                restTemplate.exchange(
                        targetUrl,
                        HttpMethod.GET,
                        null,
                        LicenseDto.class);
        LicenseDto res = response.getBody();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, res.getLicenseNo());
        assertEquals(licenseDto.getType(), res.getType());
    }
}