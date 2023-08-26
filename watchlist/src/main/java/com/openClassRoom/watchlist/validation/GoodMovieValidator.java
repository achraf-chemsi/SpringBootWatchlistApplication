package com.openClassRoom.watchlist.validation;

import com.openClassRoom.watchlist.domain.WatchlistItem;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem> {

	@Override
	public boolean isValid(WatchlistItem value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return !(Double.valueOf(value.getRating())>=8 && "L".equals(value.getPriority().trim().toUpperCase()));
	}

}
