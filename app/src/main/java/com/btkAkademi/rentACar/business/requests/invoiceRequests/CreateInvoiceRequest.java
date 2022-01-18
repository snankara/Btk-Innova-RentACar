package com.btkAkademi.rentACar.business.requests.invoiceRequests;

import lombok.NoArgsConstructor;

import java.time.LocalDate;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
	
	private int rentalId;
	
	private LocalDate invoiceDate;
}
