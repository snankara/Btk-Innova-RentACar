package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.individualCustomerDtos.IndividualCustomerDto;
import com.btkAkademi.rentACar.business.dtos.individualCustomerDtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface IndividualCustomerService {
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	Result delete(int id);
	DataResult<List<IndividualCustomerListDto>> getAll();
	DataResult<IndividualCustomerDto> getById(int id);
}
