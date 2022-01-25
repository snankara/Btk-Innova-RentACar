package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.additionalDtos.AdditionalDto;
import com.btkAkademi.rentACar.business.dtos.additionalDtos.AdditionalListDto;
import com.btkAkademi.rentACar.business.requests.additionalRequests.CreateAdditionalRequest;
import com.btkAkademi.rentACar.business.requests.additionalRequests.UpdateAdditionalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface AdditionalService {
	DataResult<AdditionalDto> getById(int id);
	//DataResult<AdditionalDto> getByRentalId(int rentalId);
	DataResult<List<AdditionalListDto>> getAll();
	Result add(CreateAdditionalRequest createAdditionalRequest);
	Result update(UpdateAdditionalRequest updateAdditionalRequest);
	Result delete(int id);
}
