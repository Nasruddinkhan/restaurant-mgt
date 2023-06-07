package com.mypractice.restaurantmgt.repository;

import com.mypractice.restaurantmgt.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
}
