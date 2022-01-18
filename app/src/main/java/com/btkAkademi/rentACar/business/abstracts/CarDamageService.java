package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.carDamageDtos.CarDamageDto;
import com.btkAkademi.rentACar.business.dtos.carDamageDtos.CarDamageListDto;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.CreateCarDamageRequest;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.UpdateCarDamageRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CarDamageService {
	Result add(CreateCarDamageRequest createCarDamageRequest);
	Result update(UpdateCarDamageRequest updateCarDamageRequest);
	Result delete(int id);
	DataResult<CarDamageDto> getById(int id);
	DataResult<List<CarDamageListDto>> getAll();
}
