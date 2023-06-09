package com.mypractice.restaurantmgt.controller;

import com.mypractice.restaurantmgt.dto.LicenseDto;
import com.mypractice.restaurantmgt.exception.RestaurantException;
import com.mypractice.restaurantmgt.service.LicenseService;
import com.mypractice.restaurantmgt.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/license")
@RequiredArgsConstructor
public class LicenseController {
    private final RestaurantService restaurantService;
    private final LicenseService licenseService;


    @PutMapping("/{restaurant-id}")
    public ResponseEntity<LicenseDto> updateLicense(@RequestBody LicenseDto licenseDto, @PathVariable("restaurant-id") Long restaurantId) {
        LicenseDto license = Optional.ofNullable(licenseDto.getLicenseNo())
                .map(e -> licenseService.addLicense(licenseDto, restaurantService.findRestaurantByRestaurantId(restaurantId)))
                .orElseThrow(() -> new RestaurantException("license no cannot be empty or null"));
        return new ResponseEntity<>(license, HttpStatus.OK);
    }

    @GetMapping("/{license-id}")
    public ResponseEntity<LicenseDto> findLicenseByLicenseId(@PathVariable("license-id") Long licenseId){
        LicenseDto license =  licenseService.findLicenseByLicenseId(licenseId);
        return  ResponseEntity.ok(license);
    }
}
