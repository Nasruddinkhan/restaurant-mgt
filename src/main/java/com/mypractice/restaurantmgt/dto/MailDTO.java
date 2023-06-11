package com.mypractice.restaurantmgt.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MailDTO {

    @JsonProperty("mail_to")
    private String mailTo;

    @JsonProperty("required_data")
    private Map<String, Object> requiredData;
}
