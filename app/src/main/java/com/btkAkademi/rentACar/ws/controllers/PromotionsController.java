package com.btkAkademi.rentACar.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.PromotionService;
import com.btkAkademi.rentACar.business.requests.promotionRequests.CreatePromotionRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/promotions")
public class PromotionsController {
	
	private PromotionService promotionService;

	@Autowired
	public PromotionsController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody CreatePromotionRequest createPromotionRequest) {
		return this.promotionService.add(createPromotionRequest);
	}
} 
