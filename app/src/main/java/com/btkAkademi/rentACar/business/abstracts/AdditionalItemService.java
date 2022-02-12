package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.additionalDtos.AdditionalListDto;
import com.btkAkademi.rentACar.business.dtos.additionalItemDtos.AdditionalItemListDto;
import com.btkAkademi.rentACar.business.requests.additionalItemRequests.CreateAdditionalItemRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface AdditionalItemService {
	Result add(List<CreateAdditionalItemRequest> additionalItemRequests);
	DataResult<List<AdditionalItemListDto>> findAllByRentalId(int rentalId);
}
