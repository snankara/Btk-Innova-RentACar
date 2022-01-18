package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.rentalDtos.RentalDto;
import com.btkAkademi.rentACar.business.dtos.rentalDtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Rental;

public interface RentalService {
	
	Result rentForIndividualCustomer(CreateRentalRequest createRentalRequest);
	Result rentForCorporateCustomer(CreateRentalRequest createRentalRequest);
	Result update(UpdateRentalRequest updateRentalRequest);
	Result delete(int id);
	DataResult<Rental> getByCarId(int carId);
	DataResult<RentalDto> getById(int id);
	DataResult<List<RentalListDto>> getAll();
}
