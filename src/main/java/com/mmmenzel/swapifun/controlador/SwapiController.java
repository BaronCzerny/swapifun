package com.mmmenzel.swapifun.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmmenzel.swapifun.modelo.Persons;
import com.mmmenzel.swapifun.servicios.SwapiService;

import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("swapi-proxy")
@Validated
public class SwapiController{

	@Autowired
	private SwapiService swapiService;
	
//	private static final Logger log = LoggerFactory.getLogger(SwapiController.class);

	@GetMapping("/person-info")
	public ResponseEntity<Persons> getResult(@RequestParam @NotBlank(message = "Parameter 'Name' can't be empty") String name) {
	    log.info("Person name: {}", name);
		
		return swapiService.getSwapiPerson(name);
	}


}
