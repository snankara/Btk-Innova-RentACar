package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.dtos.carDtos.CarDto;
import com.btkAkademi.rentACar.business.dtos.carDtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin
public class CarsController {

	private CarService carService;

	public CarsController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("getallisdeletedfalse")
	public DataResult<List<CarListDto>> getAllIsDeletedFalse(){
		return this.carService.findAllByIsDeletedFalseAndRentalStateFalse();
	}
	
	@GetMapping("getall")
	public DataResult<List<CarListDto>> getAll(){
		return this.carService.getAll();
	}
	
	@GetMapping("getbyid")
	public DataResult<CarDto> getById(@RequestParam int id){
		return this.carService.getById(id);
	}
	
	@GetMapping("getallpageable")
	public DataResult<List<CarListDto>> getAllPageable(@RequestParam int pageNo,@RequestParam(required = false) Integer pageSize){
		return this.carService.getAllPageable(pageNo, pageSize);
	}
	
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
		return this.carService.add(createCarRequest);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.carService.delete(id);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}
}
