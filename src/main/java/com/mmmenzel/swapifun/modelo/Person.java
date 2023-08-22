package com.mmmenzel.swapifun.modelo;

import java.util.List;

public record Person (String name, String birth_year, String gender,
	String planet_name, String fastest_vehicle_driven, List<Film> films){};

