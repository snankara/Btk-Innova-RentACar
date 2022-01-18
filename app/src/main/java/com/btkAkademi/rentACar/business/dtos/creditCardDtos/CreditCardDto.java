package com.btkAkademi.rentACar.business.dtos.creditCardDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDto {
	private int id;
	private String firstNameAndLastName;
	private String expirationDate;
	private String cCv;
	private String cardNumber;
}
