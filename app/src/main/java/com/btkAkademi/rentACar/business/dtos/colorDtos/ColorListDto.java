package com.btkAkademi.rentACar.business.dtos.colorDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorListDto {
	private int id;
	private String name;
	private boolean isDeleted;
}
