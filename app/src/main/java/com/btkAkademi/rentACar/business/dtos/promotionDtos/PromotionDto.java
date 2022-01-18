package com.btkAkademi.rentACar.business.dtos.promotionDtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {

	private String promotionCode;
	
	private double discountRate;
	
	private LocalDate promotionStartDate;
	
	private LocalDate promotionEndDate;
}
