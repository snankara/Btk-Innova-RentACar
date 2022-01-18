package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarDamageService;
import com.btkAkademi.rentACar.business.dtos.carDamageDtos.CarDamageDto;
import com.btkAkademi.rentACar.business.dtos.carDamageDtos.CarDamageListDto;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.CreateCarDamageRequest;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.UpdateCarDamageRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cardamages")
public class CarDamagesController {
	private CarDamageService carDamageService;

	@Autowired
	public CarDamagesController(CarDamageService carDamageService) {
		this.carDamageService = carDamageService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarDamageRequest createCarDamageRequest) {
		return this.carDamageService.add(createCarDamageRequest);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateCarDamageRequest updateCarDamageRequest) {
		return this.carDamageService.update(updateCarDamageRequest);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.carDamageService.delete(id);
	}
	
	@GetMapping("getall")
	public DataResult<List<CarDamageListDto>> getAll() {
		return this.carDamageService.getAll();
	}
	
	@GetMapping("getbyid")
	public DataResult<CarDamageDto> getById(@RequestParam int id) {
		return this.carDamageService.getById(id);
	}
}
