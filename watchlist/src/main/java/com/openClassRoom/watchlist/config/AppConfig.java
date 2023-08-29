package com.openClassRoom.watchlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openClassRoom.watchlist.repository.WatchlistRepository;
import com.openClassRoom.watchlist.service.MovieRatingService;
import com.openClassRoom.watchlist.service.WatchlistService;

@Configuration
public class AppConfig {

	@Bean
	public WatchlistRepository watchlistRepository() {
		return new WatchlistRepository();
	}

	@Bean
	public MovieRatingService movieRatingService() {
		return new MovieRatingService();
	}
	
	@Bean
	public WatchlistService watchlistService() {
		return new WatchlistService(watchlistRepository(), movieRatingService());
	}
}
