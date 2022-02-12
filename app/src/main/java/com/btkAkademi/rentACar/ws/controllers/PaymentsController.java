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

import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.dtos.paymentDtos.PaymentDto;
import com.btkAkademi.rentACar.business.dtos.paymentDtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentsController {
	private PaymentService paymentService;

	public PaymentsController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@PostMapping("add")
	public DataResult<PaymentDto> add(@RequestBody @Valid CreatePaymentRequest createPaymentRequest, @RequestParam(required = false) String promotionCode) {
		return this.paymentService.add(createPaymentRequest, promotionCode);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.paymentService.delete(id);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdatePaymentRequest updatePaymentRequest) {
		return this.paymentService.update(updatePaymentRequest);
	}
	
	@GetMapping("getall")
	public DataResult<List<PaymentListDto>> getAll() {
		return this.paymentService.getAll();
	}
	
	@GetMapping("getbyid")
	public DataResult<PaymentDto> getById(@RequestParam int id) {
		return this.paymentService.getById(id);
	}
}
