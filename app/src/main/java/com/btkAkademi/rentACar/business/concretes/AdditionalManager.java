package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.additionalDtos.AdditionalDto;
import com.btkAkademi.rentACar.business.dtos.additionalDtos.AdditionalListDto;
import com.btkAkademi.rentACar.business.requests.additionalRequests.CreateAdditionalRequest;
import com.btkAkademi.rentACar.business.requests.additionalRequests.UpdateAdditionalRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalDao;
import com.btkAkademi.rentACar.entities.concretes.Additional;

@Service
public class AdditionalManager implements AdditionalService{
	private AdditionalDao additionalDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public AdditionalManager(AdditionalDao additionalDao, ModelMapperService modelMapperService) {
		this.additionalDao = additionalDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateAdditionalRequest createAdditionalRequest) {
		Additional additional = this.modelMapperService.forRequest().map(createAdditionalRequest, Additional.class);
		this.additionalDao.save(additional);
		return new SuccessResult();
	}

	@Override
	public DataResult<AdditionalDto> getById(int id) {
		Additional additional = this.additionalDao.findById(id);
		AdditionalDto additionalDto = this.modelMapperService.forDto().map(additional, AdditionalDto.class);
		return new SuccessDataResult<AdditionalDto>(additionalDto);
	}

	/*
	@Override
	public DataResult<AdditionalDto> getByRentalId(int rentalId) {
		Additional additional = this.additionalDao.findByRentalId(rentalId);
		AdditionalDto additionalDto = this.modelMapperService.forDto().map(additional, AdditionalDto.class);
		return new SuccessDataResult<AdditionalDto>(additionalDto);
	}
	*/

	@Override
	public DataResult<List<AdditionalListDto>> getAll() {
		List<Additional> additionals = this.additionalDao.findAllByIsDeletedFalse();
		List<AdditionalListDto> response = additionals.stream().map(additional -> this.modelMapperService.forDto()
				.map(additional, AdditionalListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<AdditionalListDto>>(response);
	}

	@Override
	public Result update(UpdateAdditionalRequest updateAdditionalRequest) {
		Additional additional = this.modelMapperService.forRequest().map(updateAdditionalRequest, Additional.class);
		this.additionalDao.save(additional);
		return new SuccessResult(Messages.additionalUpdated);
	}

	@Override
	public Result delete(int id) {
		Additional additional = this.additionalDao.findById(id);
		additional.setDeleted(true);
		this.additionalDao.save(additional);
		return new SuccessResult(Messages.additionalDeleted);
	}
}
