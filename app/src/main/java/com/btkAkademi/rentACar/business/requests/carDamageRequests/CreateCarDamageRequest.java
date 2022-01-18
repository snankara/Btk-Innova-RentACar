package com.btkAkademi.rentACar.business.requests.carDamageRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {
	
	private String description;
	
	private int carId;
}
