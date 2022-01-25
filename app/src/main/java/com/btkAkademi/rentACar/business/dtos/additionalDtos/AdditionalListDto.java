package com.btkAkademi.rentACar.business.dtos.additionalDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalListDto {
	private int id;
	
	private String additionalName;
	
	private double additionalAmount;

}
