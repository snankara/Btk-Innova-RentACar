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

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.dtos.brandDtos.BrandDto;
import com.btkAkademi.rentACar.business.dtos.brandDtos.BrandListDto;
import com.btkAkademi.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/brands")
@CrossOrigin
public class BrandsController {

	private BrandService brandService;

	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@GetMapping("getall")
	public DataResult<List<BrandListDto>> getAll(){
		return this.brandService.getAll();
	}
	
	@GetMapping("getbyid")
	public DataResult<BrandDto> getById(@RequestParam int id){
		return this.brandService.getById(id);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest){
		return this.brandService.add(createBrandRequest);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest){
		return this.brandService.update(updateBrandRequest);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id){
		return this.brandService.delete(id);
	}
}
