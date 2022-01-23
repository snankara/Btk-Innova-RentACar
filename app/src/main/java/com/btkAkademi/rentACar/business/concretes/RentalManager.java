package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.carDtos.CarDto;
import com.btkAkademi.rentACar.business.dtos.corporateCustomerDtos.CorporateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.individualCustomerDtos.IndividualCustomerDto;
import com.btkAkademi.rentACar.business.dtos.rentalDtos.RentalDto;
import com.btkAkademi.rentACar.business.dtos.rentalDtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.abstracts.FindexScoreService;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.RentalDao;
import com.btkAkademi.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService{
	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CustomerService customerService;
	private CarMaintenanceService carMaintenanceService;
	private FindexScoreService findexScoreService;
	private CarService carService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	
	@Autowired
	public RentalManager(IndividualCustomerService individualCustomerService, 
			CarService carService, FindexScoreService findexScoreService, 
			CarMaintenanceService carMaintenanceService,RentalDao rentalDao, 
			ModelMapperService modelMapperService, 
			CustomerService customerService, CorporateCustomerService corporateCustomerService) {
		
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.customerService = customerService;
		this.carMaintenanceService = carMaintenanceService;
		this.findexScoreService = findexScoreService;
		this.carService = carService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
	}

	@Override
	public Result rentForIndividualCustomer(CreateRentalRequest createRentalRequest) {
		Result result = BusinessRules.run(
				checkRentalDate(createRentalRequest.getRentDate(),createRentalRequest.getReturnDate()),
				checkRentalKilometer(createRentalRequest.getRentedKilometer(),createRentalRequest.getReturnedKilometer()),
				checkRentalCustomerId(createRentalRequest.getCustomerId()),
				//checkReturnDate(createRentalRequest.getReturnDate()),
						checkCarMaintenance(createRentalRequest.getCarId()), 
						checkIndividualCustomerFindexScoreAndCarFindexScore(createRentalRequest.getCarId(), createRentalRequest.getCustomerId()),
						checkMinCustomerAge(createRentalRequest.getCarId(), createRentalRequest.getCustomerId()), checkRentalDate(createRentalRequest.getRentDate(), createRentalRequest.getReturnDate()));
		
		if(result != null) {
			return result;
		}
		
		
		int carId = getCarIdForClassType(createRentalRequest);
		
		createRentalRequest.setCarId(carId);
		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult("Rental Added !");
	}
	
	@Override
	public Result rentForCorporateCustomer(CreateRentalRequest createRentalRequest) {
		Result result = BusinessRules.run(
				checkRentalDate(createRentalRequest.getRentDate(),createRentalRequest.getReturnDate()),
				checkRentalKilometer(createRentalRequest.getRentedKilometer(),createRentalRequest.getReturnedKilometer()),
				checkRentalCustomerId(createRentalRequest.getCustomerId()),
						checkCarMaintenance(createRentalRequest.getCarId()), 
						checkCorporateCustomerFindexScoreAndCarFindexScore(createRentalRequest.getCarId(), createRentalRequest.getCustomerId()));
		
		if(result != null) {
			return result;
		}
		
		
		
		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult("Rental Added !");
	}
	
	
	private Result checkRentalDate(LocalDate rentDate, LocalDate returnDate) {
		
		if(returnDate != null &&  returnDate.isBefore(rentDate)) {
			return new ErrorResult("Tarih Hatası");
		}
		
		return new SuccessResult();
	}
	
	private int getCarIdForClassType(CreateRentalRequest createRentalRequest) {
		CarDto car = this.carService.getById(createRentalRequest.getCarId()).getData();
		var carMaintenanceState = checkCarMaintenance(createRentalRequest.getCarId()).isSuccess();
		var returnDateState = checkReturnDate(createRentalRequest.getReturnDate()).isSuccess();
		if (!carMaintenanceState || !returnDateState) {
			CarDto carDto = this.carService.findTop1ByClassTypeIdAndRentalStateIsFalse(car.getClassTypeId()).getData();
			this.carService.updateCarRentalState(carDto.getId());
			return carDto.getId();
			
		}else {
			return car.getId();
		}
		
	}
	
	private Result checkReturnDate(LocalDate returnDate) {
		
		if (LocalDate.now().isAfter(returnDate)) {
			return new SuccessResult();
		}
		
		return new ErrorResult("Araba Kirada");
	}
	
	private Result checkMinCustomerAge(int carId, int customerId) {
		
		CarDto carDto = this.carService.getById(carId).getData();
		IndividualCustomerDto individualCustomerDto = this.individualCustomerService.getById(customerId).getData();
		
		var customerAge = LocalDate.now().getYear() - individualCustomerDto.getBirthDate().getYear();
		
		if(customerAge < carDto.getMinCustomerAge()) {
			return new ErrorResult("Yaş Hatası");
		}
		
		return new SuccessResult();
	}
	
	private Result checkIndividualCustomerFindexScoreAndCarFindexScore(int carId, int individualCustomerId) {
		
		CarDto carDto = this.carService.getById(carId).getData();
		IndividualCustomerDto individualCustomerDto = this.individualCustomerService.getById(individualCustomerId).getData();
		var customerFindexScore = this.findexScoreService.calculateIndividualCustomerFindexScore(individualCustomerDto.getNationalityNumber());
		
		if(customerFindexScore < carDto.getMinFindexScore()) {
			return new ErrorResult("Findex Score Yetersiz");
		}
		
		return new SuccessResult();
	}
	
	private Result checkCorporateCustomerFindexScoreAndCarFindexScore(int carId, int corporateCustomerId) {
		
		CarDto carDto = this.carService.getById(carId).getData();
		CorporateCustomerDto corporateCustomerDto = this.corporateCustomerService.getById(corporateCustomerId).getData();
		var customerFindexScore = this.findexScoreService.calculateCorporateCustomerFindexScore(corporateCustomerDto.getTextNumber());
		
		if(customerFindexScore < carDto.getMinFindexScore()) {
			return new ErrorResult("Findex Score Yetersiz");
		}
		
		return new SuccessResult();
	}
	
	private Result checkRentalKilometer(int rentedKilometer, int returnKilometer) {
		
		if(rentedKilometer >= returnKilometer) {
			return new ErrorResult("Kilometre Hatası");
		}
		
		return new SuccessResult();
	}
	
	private Result checkRentalCustomerId(int customerId) {
		
		var customer = this.customerService.getById(customerId);
		
		if(customer.getData() == null) {
			return new ErrorResult(Messages.customerNotFound);
		}
		
		return new SuccessResult();
	}
	
	private Result checkCarMaintenance(int carId) {
		
		var carMaintenance = this.carMaintenanceService.getByCarIdAndMaintenanceReturnDateIsNull(carId);
		
		if(carMaintenance.getData() != null) {
			return new ErrorResult(Messages.carMaintenance);
		}
		
		return new SuccessResult();
	}

	@Override
	public DataResult<Rental> getByCarId(int carId) {
		
		return new SuccessDataResult<Rental>(this.rentalDao.findByCarId(carId));
	}

	@Override
	public DataResult<RentalDto> getById(int id) {
		Rental rental = this.rentalDao.findById(id);
		RentalDto rentalDto = this.modelMapperService.forDto().map(rental, RentalDto.class);
		return new SuccessDataResult<RentalDto>(rentalDto);
	}

	@Override
	public DataResult<List<RentalListDto>> getAll() {
		List<Rental> rentals = this.rentalDao.findAllByIsDeletedFalse();
		
		List<RentalListDto> response = rentals.stream().map(rental -> this.modelMapperService.forDto().map(rental, RentalListDto.class))
				.collect(Collectors.toList());
				
		return new SuccessDataResult<List<RentalListDto>>(response);
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.rentalUpdated);
	}

	@Override
	public Result delete(int id) {
		Rental rental =  this.rentalDao.findById(id);
		rental.setDeleted(true);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.rentalDeleted);
	}


	
}
