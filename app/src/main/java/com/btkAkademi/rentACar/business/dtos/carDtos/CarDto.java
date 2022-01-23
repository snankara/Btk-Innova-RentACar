package com.btkAkademi.rentACar.business.dtos.carDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
	private int id;
	private double dailyPrice;
	private int modelYear;
	private String description;
	private int kilometer;
	private int minFindexScore;
	private int classTypeId;
	private String classTypeName;
	private int minCustomerAge;
	private int brandId;
	private boolean isDeleted;
	private String brandName;
	private String colorName;
	private int colorId;
}
