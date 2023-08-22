package com.mmmenzel.swapifun.modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Result(String name, String birth_year, String gender, 
		String homeworld, List<String> films, List<String> vehicles,
		List<String> starships){}
