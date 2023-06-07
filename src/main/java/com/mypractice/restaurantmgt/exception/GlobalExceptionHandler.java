package com.mypractice.restaurantmgt.exception;

import com.mypractice.restaurantmgt.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<ErrorDto> handleRuntimeException(Exception exception){
        return ResponseEntity.internalServerError().body(ErrorDto.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .msg(exception.getMessage())
                        .localDateTime(LocalDateTime.now(ZoneId.of("UTC")))
                .build());

    }
}


