package com.mypractice.restaurantmgt.dto;

import com.mypractice.restaurantmgt.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LicenseDto {

    private Long licenseNo;
    private String licenseNumber;
    private DocumentType type;
    private String documentName;
    private String fileContent;
}
