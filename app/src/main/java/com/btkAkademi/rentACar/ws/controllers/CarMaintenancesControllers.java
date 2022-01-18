package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.dtos.carMaintenanceDtos.CarMaintenanceDto;
import com.btkAkademi.rentACar.business.dtos.carMaintenanceDtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequests.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequests.UpdateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/carmaintenances")
public class CarMaintenancesControllers {
	
	private CarMaintenanceService carMaintenanceService;

	public CarMaintenancesControllers(CarMaintenanceService carMaintenanceService) {
		this.carMaintenanceService = carMaintenanceService;
	}
	
	@GetMapping("getall")
	public DataResult<List<CarMaintenanceListDto>> getAll(){
		return this.carMaintenanceService.getAll();
	}
	
	@GetMapping("getbyid")
	public DataResult<CarMaintenanceDto> getById(@RequestParam int id){
		return this.carMaintenanceService.getById(id);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest){
		return this.carMaintenanceService.add(createCarMaintenanceRequest);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest){
		return this.carMaintenanceService.update(updateCarMaintenanceRequest);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id){
		return this.carMaintenanceService.delete(id);
	}
}
