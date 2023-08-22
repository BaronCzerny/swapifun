package com.mmmenzel.swapifun.servicios;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class VehicleService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	public VehicleService( ) {};

	private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

	@Cacheable("fastestVehicleDrivenCache")
	public String getFastestVehicleDriven(List<String> urls) {
	    Map<String, Long> vehicleSpeeds = new HashMap<>();
        JsonNode node;
        
	    for (String url : urls) {
	        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
	        String responseBody = response.getBody();
	        try {
	            node = objectMapper.readTree(responseBody);
	            String name = node.get("name").asText();
	            Long speed = node.get("max_atmosphering_speed").asLong();
	            vehicleSpeeds.put(name, speed);
	        } catch (IOException e) {
				log.error(e.getMessage());
	        }
	    }

	    String fastestVehicleDriven = null;
	    Long maxSpeed = 0L;
	    for (Map.Entry<String, Long> entry : vehicleSpeeds.entrySet()) {
	        if (entry.getValue() > maxSpeed) {
	            maxSpeed = entry.getValue();
	            fastestVehicleDriven = entry.getKey();
	        }
	    }

	    return fastestVehicleDriven;
	}

}
