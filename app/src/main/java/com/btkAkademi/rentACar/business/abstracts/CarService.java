package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.carDtos.CarDto;
import com.btkAkademi.rentACar.business.dtos.carDtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CarService {
	DataResult<List<CarListDto>> getAll();
	DataResult<CarDto> getById(int carId);
	DataResult<CarDto> findTop1ByClassTypeIdAndRentalStateIsFalse(int classTypeId);
	Result add(CreateCarRequest createCarRequest);
	Result update(UpdateCarRequest updateCarRequest);
	Result delete(int id);
	Result updateCarRentalState(int carId);
	DataResult<List<CarListDto>> getAllPageable(int pageNo, Integer pageSize);
}
