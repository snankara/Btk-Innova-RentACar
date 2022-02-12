package com.btkAkademi.rentACar.business.requests.rentalRequests;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	
	@NotNull
	private LocalDate rentDate;
	
	@Nullable
	private LocalDate returnDate;
		
	@NotNull
	private int rentedKilometer;
	
	private int customerId;
	
	private int carId;
	
	private int rentedCityId;
	
	private int returnedCityId;
	
}
