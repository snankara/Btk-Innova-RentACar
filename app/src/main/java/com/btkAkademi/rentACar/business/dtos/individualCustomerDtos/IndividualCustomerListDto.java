package com.btkAkademi.rentACar.business.dtos.individualCustomerDtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerListDto {
	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDate birthDate;
}
