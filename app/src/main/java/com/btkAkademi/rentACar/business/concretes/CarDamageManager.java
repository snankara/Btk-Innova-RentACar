package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarDamageService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.carDamageDtos.CarDamageDto;
import com.btkAkademi.rentACar.business.dtos.carDamageDtos.CarDamageListDto;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.CreateCarDamageRequest;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.UpdateCarDamageRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDamageDao;
import com.btkAkademi.rentACar.entities.concretes.CarDamage;

@Service
public class CarDamageManager implements CarDamageService{
	private CarDamageDao carDamageDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService) {
		this.carDamageDao = carDamageDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCarDamageRequest createCarDamageRequest) {
		CarDamage carDamage = this.modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
		this.carDamageDao.save(carDamage);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
		CarDamage carDamage = this.modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
		this.carDamageDao.save(carDamage);		
		return new SuccessResult(Messages.carDamageUpdated);
	}

	@Override
	public Result delete(int id) {
		CarDamage carDamage = this.carDamageDao.findById(id).get();
		carDamage.setDeleted(true);
		this.carDamageDao.save(carDamage);
		return new SuccessResult(Messages.carDamageDeleted);
	}

	@Override
	public DataResult<List<CarDamageListDto>> getAll() {
		List<CarDamage> carDamages = this.carDamageDao.findAllByIsDeletedFalse();
		List<CarDamageListDto> response = carDamages.stream().map(carDamage -> this.modelMapperService.forDto()
				.map(carDamage, CarDamageListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarDamageListDto>>(response);
	}

	@Override
	public DataResult<CarDamageDto> getById(int id) {
		CarDamage carDamage = this.carDamageDao.findById(id).get();
		CarDamageDto carDamageDto = this.modelMapperService.forDto().map(carDamage, CarDamageDto.class);
		return new SuccessDataResult<CarDamageDto>(carDamageDto);
	}
}
