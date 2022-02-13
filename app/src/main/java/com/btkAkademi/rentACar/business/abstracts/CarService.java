package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.btkAkademi.rentACar.business.dtos.carDtos.CarDto;
import com.btkAkademi.rentACar.business.dtos.carDtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Car;

public interface CarService {
	DataResult<List<CarListDto>> findAllByIsDeletedFalseAndRentalStateFalse();
	
	DataResult<List<CarListDto>> findAllByRentalStateTrue();
	
	DataResult<List<CarListDto>> getAll(int pageNo,int pageSize);
	
	DataResult<CarDto> getById(int carId);
	
	DataResult<CarDto> findTop1ByClassTypeIdAndRentalStateIsFalse(int classTypeId);
	
	Result add(CreateCarRequest createCarRequest);
	
	Result update(UpdateCarRequest updateCarRequest);
	
	Result delete(int id);
	
	Result updateCarRentalState(int carId);
	
	DataResult<List<CarListDto>> getAllPageable(int pageNo, Integer pageSize);  
}
