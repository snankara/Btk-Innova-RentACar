package com.btkAkademi.rentACar.business.dtos.rentalDtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
	private int id;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private int rentedKilometer;
	private int returnedKilometer;
	private int customerId;
	private int carId;
	private int rentedCityId;
	private int returnedCityId;;
}
