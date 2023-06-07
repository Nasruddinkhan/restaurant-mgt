package com.mypractice.restaurantmgt.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtil {
    private static final String PATTERN_DATE_TIME = "ddMMyyyyHHmmss";

    private CommonUtil(){}

    public static String getUniqueNumber(){
        return DateTimeFormatter.ofPattern(PATTERN_DATE_TIME).format(LocalDateTime.now());

    }

    public static void main(String[] args) {
        System.out.println(getUniqueNumber());
    }
}
