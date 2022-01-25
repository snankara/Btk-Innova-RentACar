package com.btkAkademi.rentACar.business.dtos.carDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListDto {
	private int id;
	private double dailyPrice;
	private int modelYear;
	private String description;
	private int minFindexScore;
	private int kilometer;
	private int brandId;
	private int classTypeId;
	private String classTypeName; 
	private boolean isDeleted;
	private String brandName;
	private int minCustomerAge;
	private String colorName;
	private int colorId;
}
