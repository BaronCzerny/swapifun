package com.mmmenzel.swapifun.restclient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mmmenzel.swapifun.ApiProperties;
import com.mmmenzel.swapifun.modelo.Results;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SwapiRestClient {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiProperties properties;
	
	public Results getRestData(String name) {
		String apiUrl = null;
		try {
			apiUrl = properties.getMainEndPoint() + URLEncoder.encode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		// Result
		ResponseEntity<Results> apiResponse = restTemplate.getForEntity(apiUrl, Results.class);
		if (apiResponse.getStatusCodeValue() == 200){
			return apiResponse.getBody();
		} else {
			log.error("Exception caused by not OK response", apiResponse.getBody()); 
		}
		return apiResponse.getBody();
	}
}
