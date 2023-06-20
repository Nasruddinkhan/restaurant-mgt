package com.mypractice.restaurantmgt.controller;

import com.mypractice.restaurantmgt.dto.RestaurantDto;
import com.mypractice.restaurantmgt.entity.MailTemplateEntity;
import com.mypractice.restaurantmgt.repository.MailTemplateRepo;
import com.mypractice.restaurantmgt.service.MailSenderService;
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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class RestaurantControllerTest {
    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate;
    private final RestaurantService restaurantService;

    private final MailTemplateRepo mailTemplateRepo;

    RestaurantDto restaurantDto;
    HttpHeaders headers;

    @Autowired
    public RestaurantControllerTest(TestRestTemplate restTemplate, RestaurantService restaurantService, MailTemplateRepo mailTemplateRepo) {
        this.restTemplate = restTemplate;
        this.restaurantService = restaurantService;
        this.mailTemplateRepo = mailTemplateRepo;
    }

    @BeforeEach
    void setUp() throws IOException {
        System.out.println("RestaurantControllerTest.setUp");
        MockitoAnnotations.openMocks(this);
        restaurantDto = CommonUtilTest.loadFile("json/restaurant-payload.json", RestaurantDto.class, getClass().getClassLoader());
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)); // Add Accept header
        restaurantService.addRestaurant(restaurantDto);
        List<MailTemplateEntity> mailTemplateEntities = new ArrayList<>();
        mailTemplateEntities = CommonUtilTest.loadFile("json/emailtemplates.json", mailTemplateEntities, getClass().getClassLoader());
        mailTemplateRepo.saveAll(mailTemplateEntities).forEach(e->System.out.println(e.getName()));


    }


    @Test
    void addRestaurant() throws URISyntaxException {
        HttpEntity<RestaurantDto> requestEntity = new HttpEntity<>(restaurantDto, headers);
        final URI targetUrl = fromUriString("http://localhost:" + port + "/restaurant-svc/api/v1/restaurant")
                .build()
                .encode()
                .toUri();
        ResponseEntity<RestaurantDto> response = restTemplate.exchange(
                targetUrl,
                HttpMethod.POST,
                requestEntity,
                RestaurantDto.class);
        RestaurantDto res = response.getBody();
        assertEquals(201, response.getStatusCodeValue());
        assertThat(res.getDishDto()).isEqualTo(restaurantDto.getDishDto());
        assertEquals(restaurantDto.getLicenseDto().getLicenseNumber(), res.getLicenseDto().getLicenseNumber());
        assertEquals(restaurantDto.getLicenseDto().getType(), res.getLicenseDto().getType());

    }

    @Test
    void findAllRestaurant() {
        System.out.println("RestaurantControllerTest.findAllRestaurant");
        final URI targetUrl = fromUriString("http://localhost:" + port + "/restaurant-svc/api/v1/restaurant")
                .build()
                .encode()
                .toUri();
        ResponseEntity<List<RestaurantDto>> response =
                restTemplate.exchange(
                        targetUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<RestaurantDto>>() {
                        });
        List<RestaurantDto> res = response.getBody();

        assertEquals(200, response.getStatusCodeValue());
        res.stream().forEach(e-> assertEquals(restaurantDto.getName(), e.getName()));

    }

    @Test
    void findRestaurantById() {
        System.out.println("RestaurantControllerTest.findRestaurantById");
        final URI targetUrl = fromUriString("http://localhost:" + port + "/restaurant-svc/api/v1/restaurant/1")
                .build()
                .encode()
                .toUri();
        ResponseEntity<RestaurantDto> response =
                restTemplate.exchange(
                        targetUrl,
                        HttpMethod.GET,
                        null,
                        RestaurantDto.class);
        RestaurantDto res = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(restaurantDto.getName(), res.getName());
        assertEquals(1, res.getRestaurantId());
    }

    @Test
    void findAllRestaurantWithDetails() {
        System.out.println("RestaurantControllerTest.findAllRestaurantWithDetails");
        final URI targetUrl = fromUriString("http://localhost:" + port + "/restaurant-svc/api/v1/restaurant/all")
                .build()
                .encode()
                .toUri();
        ResponseEntity<List<RestaurantDto>> response =
                restTemplate.exchange(
                        targetUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<RestaurantDto>>() {
                        });
        List<RestaurantDto> res = response.getBody();

        assertEquals(200, response.getStatusCodeValue());
        res.stream().forEach(e-> assertEquals(restaurantDto.getName(), e.getName()));
        assertThat(res.get(0).getDishDto()).isEqualTo(restaurantDto.getDishDto());
        assertEquals(restaurantDto.getLicenseDto().getLicenseNumber(), res.get(0).getLicenseDto().getLicenseNumber());
        assertEquals(restaurantDto.getLicenseDto().getType(), res.get(0).getLicenseDto().getType());
    }
}