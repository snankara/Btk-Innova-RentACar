package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.carMaintenanceDtos.CarMaintenanceDto;
import com.btkAkademi.rentACar.business.dtos.carMaintenanceDtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequests.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequests.UpdateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarMaintenanceDao;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;
import com.btkAkademi.rentACar.entities.concretes.Rental;

@Service
public class CarMaintenanceManager implements CarMaintenanceService{
	
	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;

	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService, @Lazy RentalService rentalService) {
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		Result result = BusinessRules.run(checkCarRented(createCarMaintenanceRequest.getCarId()));
		if (result != null) {
			return result;
		}
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		return new SuccessResult("Car maintenance added !");
	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getAll() {
		List<CarMaintenance> carMaintenanceList =  this.carMaintenanceDao.findAllByIsDeletedFalse();
		List<CarMaintenanceListDto> response = carMaintenanceList.stream().map(maintenance -> modelMapperService.forDto()
				.map(maintenance, CarMaintenanceListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response);
	}

	@Override
	public DataResult<CarMaintenance> getByCarIdAndMaintenanceReturnDateIsNull(int carId) {
		return new SuccessDataResult<CarMaintenance>(this.carMaintenanceDao.findByCarIdAndMaintenanceReturnDateIsNull(carId));

	}
	
	private Result checkCarRented(int carId) {
		
		Rental rental = this.rentalService.getByCarId(carId).getData();
		if (LocalDate.now().isBefore(rental.getReturnDate())) {
			return new ErrorResult("Araba Kirada !");
		}
		
		
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		return new SuccessResult(Messages.carMaintenanceUpdated);
	}

	@Override
	public Result delete(int id) {
		CarMaintenance carMaintenance = this.carMaintenanceDao.findById(id).get();
		carMaintenance.setDeleted(true);
		this.carMaintenanceDao.save(carMaintenance);
		return new SuccessResult(Messages.carMaintenanceDeleted);
	}

	@Override
	public DataResult<CarMaintenanceDto> getById(int id) {
		CarMaintenance carMaintenance = this.carMaintenanceDao.findById(id).get();
		CarMaintenanceDto carMaintenanceDto = this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceDto.class);
		return new SuccessDataResult<CarMaintenanceDto>(carMaintenanceDto);
	}
}
