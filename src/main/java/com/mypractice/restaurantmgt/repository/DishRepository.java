package com.mypractice.restaurantmgt.repository;

import com.mypractice.restaurantmgt.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
