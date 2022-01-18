package com.btkAkademi.rentACar.business.requests.carDamageRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamageRequest {
	
	@NotNull
	private int id;
	
	@NotNull
	private String description;
	
	@NotNull
	private int carId;
}
