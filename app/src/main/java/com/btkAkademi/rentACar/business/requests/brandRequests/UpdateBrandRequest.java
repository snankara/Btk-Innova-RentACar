package com.btkAkademi.rentACar.business.requests.brandRequests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {
	
	private int id;
	
	@NotBlank
	private String name;
}
