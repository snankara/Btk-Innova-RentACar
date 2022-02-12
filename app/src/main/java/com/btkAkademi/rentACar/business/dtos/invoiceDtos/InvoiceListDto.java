package com.btkAkademi.rentACar.business.dtos.invoiceDtos;

import java.time.LocalDate;
import java.util.List;

import com.btkAkademi.rentACar.business.dtos.additionalDtos.AdditionalDto;
import com.btkAkademi.rentACar.business.dtos.additionalDtos.AdditionalListDto;
import com.btkAkademi.rentACar.business.dtos.paymentDtos.PaymentDto;
import com.btkAkademi.rentACar.entities.concretes.Additional;
import com.btkAkademi.rentACar.entities.concretes.Payment;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceListDto {
	
	private int id;
	
	private int rentalId;
	
	private LocalDate invoiceDate;
	
	private LocalDate rentDate;
	
	private LocalDate returnDate;
	
	private int rentedKilometer;
	
	private int returnedKilometer;
	
	private String rentedCity;
	
	private String brandName;
	
	private String colorName;
	
	private String returnedCity;
	
	private String paymentAmount;
		
	private double dailyPrice;
	
	private List<AdditionalListDto> additionalListDtos;
}
