package com.example.demo;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class RecommendController {

	@Autowired
	LunchService lunchService;
	
	@GetMapping("/recommend")
	@ResponseBody
	public CompletableFuture<LunchMenu> recommendLunch() {
		
	    return lunchService.getRandomLunchAsync(); 
	}

}
