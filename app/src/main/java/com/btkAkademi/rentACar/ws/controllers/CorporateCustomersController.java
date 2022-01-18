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

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.dtos.corporateCustomerDtos.CorporateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.corporateCustomerDtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/corporatecustomers")
public class CorporateCustomersController {
	private CorporateCustomerService corporateCustomerService;

	@Autowired
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		this.corporateCustomerService = corporateCustomerService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		return this.corporateCustomerService.add(createCorporateCustomerRequest);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.corporateCustomerService.delete(id);
	}
	
	@GetMapping("getall")
	public DataResult<List<CorporateCustomerListDto>> getAll() {
		return this.corporateCustomerService.getAll();
	}
	
	@GetMapping("getbyid")
	public DataResult<CorporateCustomerDto> getById(@RequestParam int id) {
		return this.corporateCustomerService.getById(id);
	}
}
