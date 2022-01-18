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

import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.rentalDtos.RentalDto;
import com.btkAkademi.rentACar.business.dtos.rentalDtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
	private RentalService rentalService;

	@Autowired
	public RentalsController(RentalService rentalService) {
		this.rentalService = rentalService;
	}
	
	@PostMapping("rentforindividualcustomer")
	public Result rentForIndividualCustomer(@RequestBody @Valid CreateRentalRequest createRentalRequest){
		return this.rentalService.rentForIndividualCustomer(createRentalRequest);
	}
	
	@PostMapping("rentforcorporatecustomer")
	public Result rentForCorporateCustomer(@RequestBody @Valid CreateRentalRequest createRentalRequest){
		return this.rentalService.rentForCorporateCustomer(createRentalRequest);
	}
	
	@PostMapping("update")
	public Result add(@RequestBody @Valid UpdateRentalRequest updateRentalRequest){
		return this.rentalService.update(updateRentalRequest);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id){
		return this.rentalService.delete(id);
	}
	
	@GetMapping("getall")
	public DataResult<List<RentalListDto>> getAll(){
		return this.rentalService.getAll();
	}
	
	@GetMapping("getbyid")
	public DataResult<RentalDto> getById(int id){
		return this.rentalService.getById(id);
	}
}
