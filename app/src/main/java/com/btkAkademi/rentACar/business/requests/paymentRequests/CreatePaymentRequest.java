package com.btkAkademi.rentACar.business.requests.paymentRequests;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.btkAkademi.rentACar.entities.concretes.CreditCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
	
	private LocalDate paymentDate;
	
	private CreditCard creditCard;
	
	@NotNull
	private int rentalId;
}
