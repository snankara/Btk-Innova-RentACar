package com.btkAkademi.rentACar.business.requests.carRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

	@NotNull
	private double dailyPrice;
	
	@NotNull
	private int modelYear;

	@NotNull
	private String description;
	
	@NotNull
	private int minFindexScore;
	
	private int minCustomerAge;

	private int classTypeId;
	
	@NotNull
	private int kilometer;

	@NotNull
	private int brandId;

	@NotNull
	private int colorId;
}
