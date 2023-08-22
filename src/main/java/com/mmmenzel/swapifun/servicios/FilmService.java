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

import java.util.List;
import com.mmmenzel.swapifun.modelo.Film;

@Service
public class FilmService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger log = LoggerFactory.getLogger(FilmService.class);
	
    public FilmService() { };

    public List<Film> getFilms(List<String> filmsList) {
        return filmsList.stream()
                .map(this::getFilmFromUrl)
                .toList();
    }

    @Cacheable("filmsCache")
    Film getFilmFromUrl(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String responseBody = response.getBody();

        JsonNode node;
        try {
            node = objectMapper.readTree(responseBody);
            String filmName = node.get("title").asText();
            String releaseDate = node.get("release_date").asText();
            return new Film(filmName, releaseDate);
        } catch (Exception e) {
			log.error("Caught exception type: {}", e.getClass().getSimpleName()); // debugging
			log.error(e.getMessage());
            return null;
        }
    }
}
