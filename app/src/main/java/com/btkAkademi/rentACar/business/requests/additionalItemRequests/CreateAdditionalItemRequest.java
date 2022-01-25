package com.btkAkademi.rentACar.business.requests.additionalItemRequests;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalItemRequest {
	
	private int rentalId;
	
	private int additionalId;
}
