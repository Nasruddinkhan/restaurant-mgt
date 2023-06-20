package com.mypractice.restaurantmgt.repository;

import com.mypractice.restaurantmgt.entity.Restaurant;
import io.micrometer.core.instrument.binder.db.MetricsDSLContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ResponseBody
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByIsActiveIsTrue();
    Optional<Restaurant> findByRestaurantIdAndIsActiveIsTrue(Long restaurantId);

}
