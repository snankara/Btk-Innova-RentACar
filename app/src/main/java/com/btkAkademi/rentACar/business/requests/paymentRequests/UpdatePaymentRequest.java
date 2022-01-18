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
public class UpdatePaymentRequest {

	@NotNull
	private int id;
	
	@NotNull
	private LocalDate paymentDate;
	
	@NotNull
	private double paymentAmount;
	
	@NotNull
	private int rentalId;
}
