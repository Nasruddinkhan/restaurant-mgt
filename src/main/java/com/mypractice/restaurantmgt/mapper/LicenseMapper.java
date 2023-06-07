package com.mypractice.restaurantmgt.mapper;

import com.mypractice.restaurantmgt.dto.LicenseDto;
import com.mypractice.restaurantmgt.entity.License;
import com.mypractice.restaurantmgt.entity.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LicenseMapper {

    public License convertLicenseDtoToLicense(final LicenseDto licenseDto,final Restaurant restaurant){
        return License.builder()
                .licenseNo(licenseDto.getLicenseNo())
                .licenseNumber(licenseDto.getLicenseNumber())
                .documentName(licenseDto.getDocumentName())
                .type(licenseDto.getType())
                .restaurant(restaurant)
                .build();
    }

    public LicenseDto convertLicenseToLicense(License license) {
        return LicenseDto.builder()
                .licenseNo(license.getLicenseNo())
                .licenseNumber(license.getLicenseNumber())
                .documentName(license.getDocumentName())
                .type(license.getType())
                .build();
    }
}
