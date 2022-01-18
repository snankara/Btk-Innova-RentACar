package com.btkAkademi.rentACar.business.requests.corporateCustomerRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private String companyName;

	@NotNull
	private String textNumber;
}
