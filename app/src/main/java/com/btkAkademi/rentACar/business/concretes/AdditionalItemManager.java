package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalItemService;
import com.btkAkademi.rentACar.business.dtos.additionalDtos.AdditionalListDto;
import com.btkAkademi.rentACar.business.requests.additionalItemRequests.CreateAdditionalItemRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalItemDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalItem;

@Service
public class AdditionalItemManager implements AdditionalItemService{
	
	private AdditionalItemDao additionalItemDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public AdditionalItemManager(AdditionalItemDao additionalItemDao, ModelMapperService modelMapperService) {
		this.additionalItemDao = additionalItemDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(List<CreateAdditionalItemRequest> additionalItemRequests) {
		List<AdditionalItem> additionalItems = additionalItemRequests.stream().map(additionalItem -> this.modelMapperService.forRequest().map(additionalItem, AdditionalItem.class)).collect(Collectors.toList());
		this.additionalItemDao.saveAll(additionalItems);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<AdditionalListDto>> findAllByRentalId(int rentalId) {
		List<AdditionalItem> additionalItems = this.additionalItemDao.findAllByRentalId(rentalId);
		
		List<AdditionalListDto> response = additionalItems.stream().map(additionalItem -> this.modelMapperService.forDto()
				.map(additionalItem, AdditionalListDto.class)).collect(Collectors.toList());
				
		return new SuccessDataResult<List<AdditionalListDto>>(response);
	}
}
