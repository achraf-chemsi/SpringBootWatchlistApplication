package com.openClassRoom.watchlist.domain;

import com.openClassRoom.watchlist.validation.GoodMovie;
import com.openClassRoom.watchlist.validation.Priority;
import com.openClassRoom.watchlist.validation.Rating;

import jakarta.validation.constraints.*;

@GoodMovie
public class WatchlistItem {
	
	@NotBlank(message="Please enter the name of the movie")
	private String title;
	
	@Rating
	private String rating;
	
	@Priority
	private String priority;
	
	@Size(max=50, message="your comment has more than 50 caracters")
	private String comment;
	
	private Integer id;
	
	public WatchlistItem(String title, String rating, String priority, String comment, Integer id) {
		super();
		this.title = title;
		this.rating = rating;
		this.priority = priority;
		this.comment = comment;
		this.id = id;
	}

	public WatchlistItem() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
