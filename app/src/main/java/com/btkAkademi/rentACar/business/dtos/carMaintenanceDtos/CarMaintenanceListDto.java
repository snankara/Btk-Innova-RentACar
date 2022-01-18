package com.btkAkademi.rentACar.business.dtos.carMaintenanceDtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceListDto {
	
	private int id;
	private int carId;
	private LocalDate maintenanceDate;
	private LocalDate maintenanceReturnDate;
}
