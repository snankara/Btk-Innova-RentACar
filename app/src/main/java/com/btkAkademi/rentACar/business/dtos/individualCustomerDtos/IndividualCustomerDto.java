package com.btkAkademi.rentACar.business.dtos.individualCustomerDtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerDto {
	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String nationalityNumber;
	private LocalDate birthDate;
}
