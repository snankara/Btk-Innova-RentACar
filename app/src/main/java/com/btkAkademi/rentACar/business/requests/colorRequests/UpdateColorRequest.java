package com.btkAkademi.rentACar.business.requests.colorRequests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
	
	private int id;
	
	@NotBlank
	private String name;
}
