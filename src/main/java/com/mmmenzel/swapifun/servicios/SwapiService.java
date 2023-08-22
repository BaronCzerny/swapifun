package com.mmmenzel.swapifun.servicios;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmmenzel.swapifun.ApiProperties;
import com.mmmenzel.swapifun.excepciones.SwapiError;
import com.mmmenzel.swapifun.modelo.Film;
import com.mmmenzel.swapifun.modelo.Person;
import com.mmmenzel.swapifun.modelo.Persons;
import com.mmmenzel.swapifun.modelo.Result;
import com.mmmenzel.swapifun.modelo.Results;
import com.mmmenzel.swapifun.restclient.SwapiRestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SwapiService {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PlanetService planetService;

	@Autowired
	private FilmService filmService;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private SwapiRestClient swapiRestClient;
	
	public ResponseEntity<Persons> getSwapiPerson(String name) {
		ResponseEntity<Persons> response = null;
		JsonNode node;
		
//		try {		
			Results results = swapiRestClient.getRestData(name);
//			node = objectMapper.readTree(responseString);
//
//			// Documents found
//			Long found = node.get("count").asLong();
//
//		    
//		    // Si no he entendido mal la especificación, hay que devolver un 404
//		    // cuando en la respuesta count = 0.
//		    // Eventualmente se puede modificar para devolver una respuesta más amigable.
//		    if (found == 0) {
//		    	SwapiError error = new SwapiError(HttpStatus.NOT_FOUND, "Resource not found");
//	            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Persons(found, List.of()));
//	            return response;
//		    } else {
//		    	JsonNode yieldNode = node.get("results");
//				List<Result> results = Arrays.asList(objectMapper.treeToValue(yieldNode, Result[].class));
//	
	            List<Person> persons = new ArrayList<>();
	
	            for (Result result : results.getResults()) {
	
	                // Homeworld planet name
	    		    String planetName = planetService.getHomeWorldName(result.homeworld());
	    		    log.info("Home world name: {}", planetName);
	
	                // Vehicle or starship with highest Max. atmospheric speed
	    		    List<String> allVehicleAndStarshipUrls = new ArrayList<>(result.vehicles());
	    		    allVehicleAndStarshipUrls.addAll(result.starships());
	
	                String fastestVehicleDriven = vehicleService.getFastestVehicleDriven(allVehicleAndStarshipUrls);
	    		    log.info("Fastest Vehicle Driven: {}", fastestVehicleDriven);
	
	    		    // Films
	    		    List<Film> filmsList = filmService.getFilms(result.films());
	    		    log.info("Films: {}", filmsList);
	    		    
	                Person person = new Person(result.name(), result.birth_year(), result.gender(),
	                        planetName, fastestVehicleDriven, filmsList);
	
	                persons.add(person);
	            }
	
	            response = ResponseEntity.ok(new Persons(null, persons));
//	    	}
//		} catch (Exception ex) {
//			log.error("Caught exception type: {}", ex.getClass().getSimpleName());
//			log.error(ex.getMessage());
//			
//			SwapiError error = new SwapiError(HttpStatus.INTERNAL_SERVER_ERROR, "Hay ocurrido un problema", ex);
//			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Persons(null, List.of()));
//		}
		return response;
	}
}
