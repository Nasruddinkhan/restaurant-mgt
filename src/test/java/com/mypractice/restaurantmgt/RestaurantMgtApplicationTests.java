package com.mypractice.restaurantmgt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@SpringBootTest
class RestaurantMgtApplicationTests {

	@Test
	void contextLoads() {
	}
	@Bean
	public RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder().messageConverters(jsonConverter());
	}

	@Bean
	public HttpMessageConverter<?> jsonConverter() {
		System.out.println("RestaurantMgtApplicationTests.jsonConverter");
		return new MappingJackson2HttpMessageConverter();
	}
}
