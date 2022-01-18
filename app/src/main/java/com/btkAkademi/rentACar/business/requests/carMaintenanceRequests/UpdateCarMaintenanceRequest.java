package com.btkAkademi.rentACar.business.requests.carMaintenanceRequests;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {
	
	@NotNull
	private int id;
	
	@NotNull
	private LocalDate maintenanceDate;
	
	@NotNull
	private LocalDate maintenanceReturnDate;
	
	@NotNull
	private int carId;
}
