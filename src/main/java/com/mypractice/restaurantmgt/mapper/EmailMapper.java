package com.mypractice.restaurantmgt.mapper;

import com.mypractice.restaurantmgt.dto.MailDTO;
import com.mypractice.restaurantmgt.dto.RestaurantDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmailMapper {
    public MailDTO getEmailDto(RestaurantDto dto) {
        Map<String, Object> data = new HashMap<>();
        return MailDTO.builder()
                .mailTo(dto.getEmail())
                .requiredData(data)
                .build();
    }
}
