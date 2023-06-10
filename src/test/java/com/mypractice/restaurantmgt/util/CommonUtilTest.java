package com.mypractice.restaurantmgt.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypractice.restaurantmgt.exception.RestaurantException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

public class CommonUtilTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getUniqueNumber() {

    }

    public static <T> T loadFile(String fileName, Class<T> destination, ClassLoader classLoader) {

        return Optional.ofNullable(classLoader.getResourceAsStream(fileName))
                .map(InputStreamReader::new).map(BufferedReader::new).map(obj -> {
                    try {
                        return new ObjectMapper().readValue(obj, destination);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).orElseThrow(() -> new RestaurantException("something is missing"));
    }

    public static <T> List<T> loadFile(String fileName, List<T> destination, ClassLoader classLoader) {
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(bufferedReader, new TypeReference<List<T>>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
            throw new RestaurantException("Failed to load file: " + fileName);
        }
    }


}