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

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.dtos.individualCustomerDtos.IndividualCustomerDto;
import com.btkAkademi.rentACar.business.dtos.individualCustomerDtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/individualcustomers")

public class IndividualCustomersController {
	private IndividualCustomerService individualCustomerService;

	@Autowired
	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		this.individualCustomerService = individualCustomerService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest){
		return this.individualCustomerService.add(createIndividualCustomerRequest);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest ){
		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id){
		return this.individualCustomerService.delete(id);
	}
	
	@GetMapping("getall")
	public DataResult<List<IndividualCustomerListDto>> getAll(){
		return this.individualCustomerService.getAll();
	}
	
	@GetMapping("getbyid")
	public DataResult<IndividualCustomerDto> getById(@RequestParam int id){
		return this.individualCustomerService.getById(id);
	}

}
