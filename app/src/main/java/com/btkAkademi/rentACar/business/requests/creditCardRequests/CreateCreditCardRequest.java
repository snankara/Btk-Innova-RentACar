package com.btkAkademi.rentACar.business.requests.creditCardRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {
	
	@NotNull
	private String firstNameAndLastName;

	@NotNull
	private String expirationDate;

	@NotNull
	private String cCv;
	
	@NotNull
	private String cardNumber;
}
