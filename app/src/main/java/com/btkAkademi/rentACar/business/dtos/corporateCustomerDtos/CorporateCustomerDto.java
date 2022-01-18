package com.btkAkademi.rentACar.business.dtos.corporateCustomerDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerDto {
	private int customerId;
	private String companyName;
	private String textNumber;
	private String email;
	private String password;
}
