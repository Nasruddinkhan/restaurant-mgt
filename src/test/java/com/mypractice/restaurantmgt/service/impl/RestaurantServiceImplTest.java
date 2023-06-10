package com.mypractice.restaurantmgt.service.impl;

import com.mypractice.restaurantmgt.dto.LicenseDto;
import com.mypractice.restaurantmgt.dto.RestaurantDto;
import com.mypractice.restaurantmgt.dto.RestaurantResponseDto;
import com.mypractice.restaurantmgt.entity.License;
import com.mypractice.restaurantmgt.entity.Restaurant;
import com.mypractice.restaurantmgt.mapper.RestaurantMapper;
import com.mypractice.restaurantmgt.repository.RestaurantRepository;
import com.mypractice.restaurantmgt.service.DishService;
import com.mypractice.restaurantmgt.service.LicenseService;
import com.mypractice.restaurantmgt.service.RestaurantService;
import com.mypractice.restaurantmgt.util.CommonUtilTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RestaurantServiceImplTest {

    RestaurantDto restaurantDto;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Mock
    private RestaurantMapper restaurantMapper;
    @Mock
    private DishService dishService;
    @Mock
    private LicenseService licenseService;

    @BeforeEach
    void setUp() {
        System.out.println("RestaurantControllerTest.setUp");
        MockitoAnnotations.openMocks(this);
        restaurantDto = CommonUtilTest.loadFile("json/restaurant-payload.json", RestaurantDto.class, getClass().getClassLoader());
    }

    @Test
    void addRestaurant() {
        Restaurant restaurant = Restaurant.builder().restaurantId(restaurantDto.getRestaurantId())
                .name(restaurantDto.getName())
                .build();
        when(restaurantMapper.restaurantDtoToRestaurant(restaurantDto)).thenReturn(restaurant);
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
        when(licenseService.addLicense(restaurantDto.getLicenseDto(), restaurant)).thenReturn(restaurantDto.getLicenseDto());
        when(restaurantMapper.restaurantToRestaurantDto(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(restaurantDto);
        RestaurantDto restaurantDt = restaurantService.addRestaurant(restaurantDto);
        assertNotNull(restaurantDt);
        assertThat(restaurantDt).isEqualTo(restaurantDto);

    }

    @Test
    void findAllRestaurant() {

        Restaurant restaurant = Restaurant.builder().restaurantId(restaurantDto.getRestaurantId())
                .name(restaurantDto.getName())
                .build();
        RestaurantResponseDto restaurantResponseDto = RestaurantResponseDto.builder()
                .restaurantId(restaurantDto.getRestaurantId())
                .name(restaurantDto.getName())
                .build();
        when(restaurantMapper.restaurantDtoToRestaurant(restaurantDto)).thenReturn(restaurant);
        when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant));
        when(restaurantMapper.restaurantToRestaurantDto(Mockito.any())).thenReturn(RestaurantResponseDto.builder()
                .restaurantId(restaurantDto.getRestaurantId())
                .name(restaurantDto.getName())
                .build());
        List<RestaurantResponseDto> restaurantDt = restaurantService.findAllRestaurant();
        System.out.println(restaurantDt.size());
        assertNotNull(restaurantDt);
        assertThat(restaurantDt).isEqualTo(Arrays.asList(restaurantResponseDto));
    }

    @Test
    void findRestaurantById() {
    }

    @Test
    void findRestaurantByRestaurantId() {
    }

    @Test
    void findAllRestaurantWithDetails() {
    }

    @Test
    void createDishDto() {
    }

    @Test
    void addLicences() {
    }
}