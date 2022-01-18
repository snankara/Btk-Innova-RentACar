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

import com.btkAkademi.rentACar.business.abstracts.ColorService;
import com.btkAkademi.rentACar.business.dtos.colorDtos.ColorDto;
import com.btkAkademi.rentACar.business.dtos.colorDtos.ColorListDto;
import com.btkAkademi.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.btkAkademi.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/colors")
@CrossOrigin
public class ColorsController {

	private ColorService colorService;

	public ColorsController(ColorService colorService) {
		this.colorService = colorService;
	}
	
	@GetMapping("getall")
	public DataResult<List<ColorListDto>> getAll(){
		return this.colorService.getAll();
	}
	
	@GetMapping("getbyid")
	public DataResult<ColorDto> getById(@RequestParam int id){
		return this.colorService.getById(id);
	}
	
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) {
		return this.colorService.add(createColorRequest);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest) {
		return this.colorService.update(updateColorRequest);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.colorService.delete(id);
	}
}
