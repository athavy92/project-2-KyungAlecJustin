package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Watchlist;
import com.revature.services.WatchlistService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WatchlistController {
	
	@Autowired
	private WatchlistService watchlistService;
	
	@GetMapping("/watchlists")
	public List<Watchlist> listAllWatchlists(){
		return watchlistService.getAllWatchlist();
		
	}
	
//	@PutMapping("/createwatchlist")
//	public ResponseEntity<Watchlist> upsert(@RequestBody Watchlist w){
//		System.out.println("reaching watchlist controller " + w);
//		
//		
//		Watchlist response = watchlistService.createWatchlist(w);
//		System.out.println("WL response: " + response);
//		return ResponseEntity.ok(response);
//	}
	
	@PutMapping("/createwatchlist")
	public String upsert(@RequestBody Watchlist watchlistCreate) throws JsonProcessingException{
		System.out.println("reaching watchlist controller " + watchlistCreate);
		
		ObjectMapper om = new ObjectMapper();
		String response;
		
		try {
			this.watchlistService.createWatchlist(watchlistCreate);
			response = om.writeValueAsString(new ResponseEntity<String>(HttpStatus.CREATED));
			return response;
		} catch (RuntimeException e) {
			response = om.writeValueAsString(new ResponseEntity<String>(HttpStatus.UNAUTHORIZED));
			return response;
		}
		
		
	}
	

}
