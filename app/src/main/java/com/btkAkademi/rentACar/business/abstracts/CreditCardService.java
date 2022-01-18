package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.creditCardDtos.CreditCardDto;
import com.btkAkademi.rentACar.business.dtos.creditCardDtos.CreditCardListDto;
import com.btkAkademi.rentACar.business.requests.creditCardRequests.CreateCreditCardRequest;
import com.btkAkademi.rentACar.business.requests.creditCardRequests.UpdateCreditCardRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CreditCardService {
	Result add(CreateCreditCardRequest createCreditCardRequest);
	Result update(UpdateCreditCardRequest updateCreditCardRequest);
	DataResult<CreditCardDto> getById(int id);
	DataResult<List<CreditCardListDto>> getAll();
	Result delete(int id);
}
