package com.openClassRoom.watchlist;



import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.openClassRoom.watchlist.domain.WatchlistItem;
import com.openClassRoom.watchlist.repository.WatchlistRepository;
import com.openClassRoom.watchlist.service.MovieRatingService;
import com.openClassRoom.watchlist.service.WatchlistService;



@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceTest {
	
	@InjectMocks
	private WatchlistService watchlistService;
	
	@Mock
	private WatchlistRepository watchlistRepositoryMock;
	
	@Mock
	private MovieRatingService movieRatingServiceMock;
	
	@Test
	public void testGetWatchlistItemsReturnsAllItemsFromRepository() {
		
		//Arrange
		WatchlistItem item1 = new WatchlistItem("Star Wars", "7.7", "M" , "" , 1);
		WatchlistItem item2 = new WatchlistItem("Star Treck", "8.8", "M" , "" , 2);
		List<WatchlistItem> mockItems = Arrays.asList(item1, item2);
		
		Mockito.when(watchlistRepositoryMock.getList()).thenReturn(mockItems);
		
		//Act
		List<WatchlistItem> result = watchlistService.getWatchlistItems();
		
		//Assert
		Assert.assertTrue(result.size() == 2);
		Assert.assertTrue(result.get(0).getTitle().equals("Star Wars"));
		Assert.assertTrue(result.get(1).getTitle().equals("Star Treck"));
	}
}