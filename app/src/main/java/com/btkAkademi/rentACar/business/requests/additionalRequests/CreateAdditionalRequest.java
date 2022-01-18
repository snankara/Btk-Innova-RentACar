package com.btkAkademi.rentACar.business.requests.additionalRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalRequest {
	
	@NotNull
	private String additionalName;
	
	@NotNull
	private double additionalAmount;
	
	@NotNull
	private int rentalId;
}
