package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;	
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.AdditionalItemService;
import com.btkAkademi.rentACar.business.requests.additionalItemRequests.CreateAdditionalItemRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/additionalitems")
@CrossOrigin
public class AdditionalItemsController {
	
	private AdditionalItemService additionalItemService;

	@Autowired
	public AdditionalItemsController(AdditionalItemService additionalItemService) {
		this.additionalItemService = additionalItemService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody List<CreateAdditionalItemRequest> additionalItemRequests) {
		return this.additionalItemService.add(additionalItemRequests);
	}
}
