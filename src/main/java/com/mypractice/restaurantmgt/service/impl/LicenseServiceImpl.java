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
        licenseDto.setDocumentName(FileUtils.createFile(licenseDto.getFileContent(), filePath, licenseDto.getDocumentName()));
        License license = repository.save(licenseMapper.convertLicenseDtoToLicense(licenseDto, restaurant));
        LicenseDto licenseDtos = licenseMapper.convertLicenseToLicense(license);
        return licenseDtos;
    }
}
