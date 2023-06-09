package com.mypractice.restaurantmgt.service.impl;

import com.mypractice.restaurantmgt.dto.LicenseDto;
import com.mypractice.restaurantmgt.entity.License;
import com.mypractice.restaurantmgt.entity.Restaurant;
import com.mypractice.restaurantmgt.mapper.LicenseMapper;
import com.mypractice.restaurantmgt.repository.LicenseRepository;
import com.mypractice.restaurantmgt.service.LicenseService;
import com.mypractice.restaurantmgt.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
@Slf4j
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {
    private final LicenseMapper licenseMapper;
    private final LicenseRepository repository;
    @Value("${file-location}")
    private String filePath;

    @Override
    @Transactional
    public LicenseDto addLicense(LicenseDto licenseDto, Restaurant restaurant) {
        licenseDto.setDocumentName(FileUtils.createFile(licenseDto.getFileContent(), filePath, licenseDto.getDocumentName(), licenseDto.getType()));
        License license = repository.save(licenseMapper.convertLicenseDtoToLicense(licenseDto, restaurant));
        return licenseMapper.convertLicenseToLicense(license);
    }

    @Override
    public LicenseDto findLicenseByRestaurant(Restaurant restaurant) {
        return repository.findByRestaurant(restaurant)
                .map(licenseMapper::convertLicenseToLicense)
                .map(this::getFileContent)
                .orElseThrow(() -> new RuntimeException("no record found for this id"));
    }

    private LicenseDto getFileContent(LicenseDto licenseDto) {
        String encodedContent = FileUtils.encodeFileToBase64(filePath + File.separator + licenseDto.getDocumentName());
        licenseDto.setFileContent(encodedContent);
        return licenseDto;
    }
}
