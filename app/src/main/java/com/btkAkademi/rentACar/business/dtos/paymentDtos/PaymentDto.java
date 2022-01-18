package com.btkAkademi.rentACar.business.dtos.paymentDtos;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
	private int id;
	private int rentalId;
	private double paymentAmount;
	private LocalDate paymentDate;
}
