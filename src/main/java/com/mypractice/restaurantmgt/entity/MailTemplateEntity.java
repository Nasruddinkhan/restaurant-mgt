package com.mypractice.restaurantmgt.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mypractice.restaurantmgt.util.JsonMapConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "mail_template")
public class MailTemplateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("required_data")
    @Convert(converter = JsonMapConverter.class)
    private Map<String, String> requiredData;
}
