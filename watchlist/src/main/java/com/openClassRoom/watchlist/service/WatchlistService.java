package com.openClassRoom.watchlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openClassRoom.watchlist.domain.WatchlistItem;
import com.openClassRoom.watchlist.exception.DuplicatedTitleException;
import com.openClassRoom.watchlist.repository.WatchlistRepository;


public class WatchlistService {
	
	WatchlistRepository watchlistRepository;
	MovieRatingService movieRatingService;
	
	public WatchlistService(WatchlistRepository watchlistRepository, MovieRatingService movieRatingService) {
		super();
		this.watchlistRepository = watchlistRepository;
		this.movieRatingService = movieRatingService;
	}

	public List<WatchlistItem> getWatchlistItems(){
		
		List<WatchlistItem> watchlistItems = watchlistRepository.getList();
		for(WatchlistItem watchlistItem: watchlistItems) {
			String rating = movieRatingService.getMovieRating(watchlistItem.getTitle());
			if(rating != null) {				
				watchlistItem.setRating(rating);
			}
		}
		return watchlistItems;
	}
	
	public int getWatchlistItemSize() {
		return watchlistRepository.getList().size();
	}
	
	public WatchlistItem findWatchlistItemById(Integer id) {
		return watchlistRepository.findById(id);
	}
	
	public void addOrUpdateWatchlistItem(WatchlistItem watchlistItem) throws DuplicatedTitleException {
		WatchlistItem existing = findWatchlistItemById(watchlistItem.getId());
		if(existing == null) {
			if(watchlistRepository.findByTitle(watchlistItem.getTitle())!=null) {
				throw new DuplicatedTitleException();
				//bindingResult.rejectValue("title", "", "this movie already exist on watch list item");
				//return new ModelAndView("watchlistItemForm");
			}
			watchlistRepository.addItem(watchlistItem);
		}else {
			existing.setComment(watchlistItem.getComment());
			existing.setPriority(watchlistItem.getPriority());
			existing.setRating(watchlistItem.getRating());
			existing.setTitle(watchlistItem.getTitle());
		}
	}
}
