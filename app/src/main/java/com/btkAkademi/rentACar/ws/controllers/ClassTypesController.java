package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.ClassTypeService;
import com.btkAkademi.rentACar.business.dtos.classTypeDtos.ClassTypeListDto;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;

@RestController
@RequestMapping("/api/classtypes")
@CrossOrigin
public class ClassTypesController {
	
	private ClassTypeService classTypeService;

	@Autowired
	public ClassTypesController(ClassTypeService classTypeService) {
		this.classTypeService = classTypeService;
	}
	
	@GetMapping("getall")
	public DataResult<List<ClassTypeListDto>> getAll(){
		return this.classTypeService.getAll();
	}
}
