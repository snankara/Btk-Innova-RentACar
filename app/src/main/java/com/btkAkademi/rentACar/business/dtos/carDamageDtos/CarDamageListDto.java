package com.btkAkademi.rentACar.business.dtos.carDamageDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDamageListDto {
	private int id;
	private int carId;
	private String description;
}
