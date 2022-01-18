package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CreditCardService;
import com.btkAkademi.rentACar.business.dtos.creditCardDtos.CreditCardDto;
import com.btkAkademi.rentACar.business.dtos.creditCardDtos.CreditCardListDto;
import com.btkAkademi.rentACar.business.requests.creditCardRequests.CreateCreditCardRequest;
import com.btkAkademi.rentACar.business.requests.creditCardRequests.UpdateCreditCardRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/creditcards")
public class CreditCardsController {
	
	private CreditCardService creditCardService;

	public CreditCardsController(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCreditCardRequest createCreditCardRequest) {
		return this.creditCardService.add(createCreditCardRequest);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.creditCardService.delete(id);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateCreditCardRequest updateCreditCardRequest) {
		return this.creditCardService.update(updateCreditCardRequest);
	}
	
	@GetMapping("getall")
	public DataResult<List<CreditCardListDto>> getAll() {
		return this.creditCardService.getAll();
	}
	
	@GetMapping("getbyid")
	public DataResult<CreditCardDto> getById(@RequestParam int id) {
		return this.creditCardService.getById(id);
	}
}
