package com.mypractice.restaurantmgt.service;

import com.mypractice.restaurantmgt.dto.LicenseDto;
import com.mypractice.restaurantmgt.entity.Restaurant;

public interface LicenseService {
    LicenseDto addLicense(LicenseDto licenseDto, Restaurant restaurant);
}