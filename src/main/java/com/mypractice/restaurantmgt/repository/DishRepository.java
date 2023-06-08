package com.mypractice.restaurantmgt.repository;

import com.mypractice.restaurantmgt.entity.Dish;
import com.mypractice.restaurantmgt.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByRestaurant(Restaurant restaurant);
}
