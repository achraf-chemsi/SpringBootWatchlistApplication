package com.openClassRoom.watchlist.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.openClassRoom.watchlist.domain.WatchlistItem;
import com.openClassRoom.watchlist.exception.DuplicatedTitleException;
import com.openClassRoom.watchlist.service.WatchlistService;

import jakarta.validation.Valid;

@Controller
public class WatchlistController {
	
	WatchlistService watchlistService;
	
	@Autowired
	public WatchlistController(WatchlistService watchlistService) {
		super();
		this.watchlistService = watchlistService;
	}



	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistItemForm(@RequestParam(required = false) Integer id) {
		
		String viewName = "watchlistItemForm";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		WatchlistItem watchlistItem = watchlistService.findWatchlistItemById(id);
		
		if(watchlistItem == null) {
			model.put("watchlistItem", new WatchlistItem() );
		}else {
			model.put("watchlistItem", watchlistItem);
		}
		return new ModelAndView(viewName, model);
	} 	



	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem ,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return new ModelAndView("watchlistItemForm");
		}
		
		try {
			watchlistService.addOrUpdateWatchlistItem(watchlistItem);
		} catch (DuplicatedTitleException e) {
			bindingResult.rejectValue("title", "", "this movie already exist on watch list item");
			return new ModelAndView("watchlistItemForm");
		}
		
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/watchlist");
		
		return new ModelAndView(redirect);
	}
	

	
	@GetMapping("/watchlist")
	public ModelAndView getWatchList() {
		
		String viewName = "watchlist";
		
		Map<String,Object> model = new HashMap<String,Object>();
		
		model.put("watchlistItems", watchlistService.getWatchlistItems());
		model.put("NumberOfWatchlist", watchlistService.getWatchlistItemSize());
		
		return new ModelAndView(viewName, model);
		
	}
}
