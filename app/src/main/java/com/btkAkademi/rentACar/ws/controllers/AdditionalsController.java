package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.AdditionalService;
import com.btkAkademi.rentACar.business.dtos.additionalDtos.AdditionalDto;
import com.btkAkademi.rentACar.business.dtos.additionalDtos.AdditionalListDto;
import com.btkAkademi.rentACar.business.requests.additionalRequests.CreateAdditionalRequest;
import com.btkAkademi.rentACar.business.requests.additionalRequests.UpdateAdditionalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionals")
public class AdditionalsController {
	
	private AdditionalService additionalService;

	public AdditionalsController(AdditionalService additionalService) {
		this.additionalService = additionalService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateAdditionalRequest createAdditionalRequest) {
		return this.additionalService.add(createAdditionalRequest);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateAdditionalRequest updateAdditionalRequest) {
		return this.additionalService.update(updateAdditionalRequest);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.additionalService.delete(id);
	}
	
	@GetMapping("getall")
	public DataResult<List<AdditionalListDto>> getAll(){
		return this.additionalService.getAll();
	}
	
	@GetMapping("getbyid")
	public DataResult<AdditionalDto> getById(@RequestParam int id){
		return this.additionalService.getById(id);
	}
}
