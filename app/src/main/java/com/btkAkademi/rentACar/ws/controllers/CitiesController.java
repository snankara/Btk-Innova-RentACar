package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.dtos.cityDtos.CityListDto;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin
public class CitiesController {
	
	private CityService cityService;

	@Autowired
	public CitiesController(CityService cityService) {
		this.cityService = cityService;
	}
	
	@GetMapping("getall")
	public DataResult<List<CityListDto>> getAll(){
		return this.cityService.getAll();
	}
}
