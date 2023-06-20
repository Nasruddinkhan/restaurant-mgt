package com.mypractice.restaurantmgt.service.impl;

import com.mypractice.restaurantmgt.dto.DishDto;
import com.mypractice.restaurantmgt.dto.LicenseDto;
import com.mypractice.restaurantmgt.dto.RestaurantDto;
import com.mypractice.restaurantmgt.dto.RestaurantResponseDto;
import com.mypractice.restaurantmgt.entity.Restaurant;
import com.mypractice.restaurantmgt.mapper.RestaurantMapper;
import com.mypractice.restaurantmgt.repository.RestaurantRepository;
import com.mypractice.restaurantmgt.service.DishService;
import com.mypractice.restaurantmgt.service.LicenseService;
import com.mypractice.restaurantmgt.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository repository;
    private final RestaurantMapper restaurantMapper;
    private final DishService dishService;
    private final LicenseService licenseService;

    @Override
    @Transactional
    public RestaurantDto addRestaurant(RestaurantDto dto) {
        Restaurant restaurant = repository.save(restaurantMapper.restaurantDtoToRestaurant(dto));
        List<DishDto> dishes = createDishDto(dto.getDishDto(), restaurant);
        LicenseDto licences = addLicences(dto.getLicenseDto(), restaurant);
        return restaurantMapper.restaurantToRestaurantDto(restaurant, dishes, licences);
    }

    @Override
    public List<RestaurantResponseDto> findAllRestaurant() {
        return repository.findByIsActiveIsTrue()
                .stream().map(restaurantMapper::restaurantToRestaurantDto)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantResponseDto findRestaurantById(Long restaurantId) {
        return repository.findByRestaurantIdAndIsActiveIsTrue(restaurantId)
                .map(restaurantMapper::restaurantToRestaurantDto)
                .orElseThrow(() -> new RuntimeException(String.format("restaurant not found given %s id", restaurantId)));
    }

    @Override
    public Restaurant findRestaurantByRestaurantId(Long restaurantId) {
        return repository.findByRestaurantIdAndIsActiveIsTrue(restaurantId)
                .orElseThrow(() -> new RuntimeException(String.format("restaurant not found given %s id", restaurantId)));
    }

    @Override
    public List<RestaurantDto> findAllRestaurantWithDetails() {
        return repository.findByIsActiveIsTrue().stream().map(this::getDishesAndLicense).collect(Collectors.toList());
    }

    private RestaurantDto getDishesAndLicense(Restaurant restaurant) {
        List<DishDto> dishDto = dishService.findAllDishesByRestaurant(restaurant);
        LicenseDto licenseDto = licenseService.findLicenseByRestaurant(restaurant);
        return restaurantMapper.restaurantToRestaurantDto(restaurant, dishDto, licenseDto);
    }



    private List<DishDto> createDishDto(List<DishDto> dishDto, Restaurant restaurant) {
        return Optional.ofNullable(dishDto)
                .map(dishDtos -> dishService.addDishes(dishDtos, restaurant))
                .orElseThrow(() -> new RuntimeException("Please provide the dishes!"));
    }

    private LicenseDto addLicences(LicenseDto licenseDto, Restaurant restaurant) {
        return Optional.of(licenseDto)
                .map(obj -> licenseService.addLicense(obj, restaurant))
                .orElseThrow(() -> new RuntimeException("Please provide the license!"));
    }
}
