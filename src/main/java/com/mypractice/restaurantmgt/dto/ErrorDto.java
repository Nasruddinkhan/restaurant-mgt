package com.mypractice.restaurantmgt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    private  String errorCode;
    private  String msg;
    private LocalDateTime localDateTime;
}
