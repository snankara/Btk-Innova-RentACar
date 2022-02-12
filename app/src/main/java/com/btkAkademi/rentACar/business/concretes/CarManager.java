package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.ColorService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.carDtos.CarDto;
import com.btkAkademi.rentACar.business.dtos.carDtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDao;
import com.btkAkademi.rentACar.entities.concretes.Car;

@Service
public class CarManager implements CarService{
	
	private CarDao carDao;
	private ModelMapperService modelMapperService;
	private BrandService brandService;
	private ColorService colorService;

	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService, BrandService brandService, ColorService colorService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
		this.brandService = brandService;
		this.colorService = colorService;
	}
	
	public DataResult<List<CarListDto>> findAllByIsDeletedFalseAndRentalStateFalse(){
		
		List<Car> carList = this.carDao.findAllByIsDeletedFalseAndRentalStateFalse();		
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto()
				.map(car, CarListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarListDto>>(response);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Result result = BusinessRules.run(
				checkBrandId(createCarRequest.getBrandId()),
				checkColorId(createCarRequest.getColorId()));
		
		if(result != null) {
			return result;
		}
		
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult(Messages.carAdded);
	}
	
	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		Result result = BusinessRules.run(
				checkCarId(updateCarRequest.getId()),
				checkBrandId(updateCarRequest.getBrandId()),
				checkColorId(updateCarRequest.getColorId()));
		
		if(result != null) {
			return result;
		}
		
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult(Messages.carUpdated);
	}
	
	private Result checkBrandId(int brandId) {
		var brand = this.brandService.getById(brandId);
		if(brand.getData() == null) {
			return new ErrorResult(Messages.brandNotFound);
		}
		
		return new SuccessResult();
	}
	
	private Result checkColorId(int colorId) {
		var color = this.colorService.getById(colorId);
		if(color.getData() == null) {
			return new ErrorResult(Messages.colorNotFound);
		}
		
		return new SuccessResult();
	}

	private Result checkCarId(int carId) {
		var car = this.carDao.findById(carId);
		if(car == null) {
			return new ErrorResult(Messages.carNotFound);
		}
		
		return new SuccessResult();
	}

	@Override
	public DataResult<CarDto> getById(int carId) {
		Car car = this.carDao.findById(carId);
		CarDto carDto = this.modelMapperService.forDto().map(car, CarDto.class);
		return new SuccessDataResult<CarDto>(carDto);
	}

	
	@Override
	public DataResult<List<CarListDto>> getAllPageable(int pageNo, Integer pageSize) {
	
		pageSize = pageSize == null ? 10 : pageSize;
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		List<Car> carList = this.carDao.findAll(pageable).getContent();		
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto()
				.map(car, CarListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarListDto>>(response);
	}

	@Override
	public Result delete(int id) {
		Car car = this.carDao.findById(id);
		car.setDeleted(true);
		this.carDao.save(car);
		return new SuccessResult(Messages.carDeleted);
	}

	@Override
	public DataResult<CarDto> findTop1ByClassTypeIdAndRentalStateIsFalse(int classTypeId) {
		Car car = this.carDao.findTop1ByClassTypeIdAndRentalStateIsFalse(classTypeId);
		if (car != null) {
			CarDto carDto = this.modelMapperService.forDto().map(car, CarDto.class);
			return new SuccessDataResult<CarDto>(carDto);
		}
		return new ErrorDataResult<CarDto>("Aynı Sınıftan Başka Araç Bulunamadı !");
		
	}

	@Override
	public Result updateCarRentalState(int carId) {
		Car car = this.carDao.findById(carId);
		car.setRentalState(true);
		this.carDao.save(car);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<CarListDto>> getAll() {
		List<Car> carList =  this.carDao.findAll();	
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto()
					.map(car, CarListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarListDto>>(response);
	}

	@Override
	public DataResult<List<CarListDto>> findAllByRentalStateTrue() {
		
		List<Car> carList = this.carDao.findAllByRentalStateTrue();		
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto()
				.map(car, CarListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarListDto>>(response);
	}
	
}
