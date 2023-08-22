package com.mmmenzel.swapifun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties(ApiProperties.class)
public class SwapiFunApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwapiFunApplication.class, args);
	}
}

