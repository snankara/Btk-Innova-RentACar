package com.btkAkademi.rentACar.business.requests.carMaintenanceRequests;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {
	
	@NotNull
	private LocalDate maintenanceDate;
	
	private LocalDate maintenanceReturnDate;
	
	private int carId;
}
