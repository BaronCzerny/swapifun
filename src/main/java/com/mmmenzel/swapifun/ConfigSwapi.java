package com.mmmenzel.swapifun;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ConfigSwapi {
	@Bean
	CacheManager cacheManager() {
	    return new ConcurrentMapCacheManager("filmsCache", "planetNameCache", 
	    									"fastestVehicleDrivenCache");
	}

	@Bean
	RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	@Bean
	ObjectMapper objectMapper() {
	    return new ObjectMapper();
	}
}
