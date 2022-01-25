package com.btkAkademi.rentACar.business.dtos.additionalItemDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalItemListDto {
	private int id;
	
	private int additionalId;
	
	private int rentalId;
}
