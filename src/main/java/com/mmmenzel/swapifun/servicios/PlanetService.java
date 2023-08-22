package com.mmmenzel.swapifun.servicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PlanetService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	public PlanetService( ) {};

	private static final Logger log = LoggerFactory.getLogger(PlanetService.class);
	
	@Cacheable("planetNameCache")
	public String getHomeWorldName(String url) {

		String planetName = "";
		JsonNode node;
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
	    String responseBody = response.getBody();
        try {
        	node = objectMapper.readTree(responseBody);
        	planetName = node.get("name").asText();
		} catch (Exception e) {
			log.error(e.getMessage());			
		}

        return planetName;
	}

}