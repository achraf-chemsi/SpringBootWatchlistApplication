package com.openClassRoom.watchlist.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class MovieRatingService {

	String apiUrl = "https://omdbapi.com/?apikey=61cbe7e9&t=";
	
	public String getMovieRating(String title) {
		try {
			RestTemplate template = new RestTemplate();
			ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl + title, ObjectNode.class);
			ObjectNode JsonObject = response.getBody();
			return JsonObject.path("imdbRating").asText();
		} catch (Exception e) {
			System.out.println("Something went wront while calling OMDb API" + e.getMessage());
			return null;
		}
	}
}
