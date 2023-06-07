package com.mypractice.restaurantmgt.repository;

import com.mypractice.restaurantmgt.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
