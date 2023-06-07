package com.mypractice.restaurantmgt.service.impl;

import com.mypractice.restaurantmgt.dto.DishDto;
import com.mypractice.restaurantmgt.entity.Dish;
import com.mypractice.restaurantmgt.entity.Restaurant;
import com.mypractice.restaurantmgt.mapper.DishMapper;
import com.mypractice.restaurantmgt.repository.DishRepository;
import com.mypractice.restaurantmgt.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishMapper dishMapper;
    private final DishRepository repository;

    @Override
    public DishDto addDishes(List<DishDto> dishDtos) {
        return null;
    }

    @Override
    @Transactional
    public List<DishDto> addDishes(List<DishDto> dishDtos, Restaurant restaurant) {
        List<Dish> dishes = dishDtos.stream().map(e -> dishMapper.convertDishDtoToDish(e, restaurant)).collect(Collectors.toList());
        List<DishDto> dishesDto = repository.saveAll(dishes)
                .stream()
                .map(dishMapper::convertDishToDishDto).collect(Collectors.toList());
        return dishesDto;
    }
}
