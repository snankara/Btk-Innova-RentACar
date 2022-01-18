package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.individualCustomerDtos.IndividualCustomerDto;
import com.btkAkademi.rentACar.business.dtos.individualCustomerDtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService{
	
	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService) {
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		
		Result result = BusinessRules.run(
				checkIfEmailExists(createIndividualCustomerRequest.getEmail()), 
				checkIndividualCustomerBirthDate(createIndividualCustomerRequest.getBirthDate()));
		
		if(result != null) {
			return result;
		}
		
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult();
	}
	
	private Result checkIfEmailExists(String email) {
		
		IndividualCustomer individualCustomer = this.individualCustomerDao.findByEmail(email);
		
		if(individualCustomer != null) {
			return new ErrorResult(Messages.customerAlreadyExists);
		}
		
		return new SuccessResult();
	}
	
	private Result checkIndividualCustomerBirthDate(LocalDate birthDate) {
			
		var result = LocalDate.now().getYear()  - birthDate.getYear();
		
		if(result < 18) {
			return new ErrorResult("Yaş Hatası");								
		}	
		
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.individualCustomerUpdated);
	}

	@Override
	public DataResult<List<IndividualCustomerListDto>> getAll() {
		List<IndividualCustomer> individualCustomers = this.individualCustomerDao.findAllByIsDeletedFalse();
		
		List<IndividualCustomerListDto> response = individualCustomers.stream()
				.map(individualCustomer -> this.modelMapperService.forDto().map(individualCustomer, IndividualCustomerListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<IndividualCustomerListDto>>(response);
	}

	@Override
	public Result delete(int id) {
		IndividualCustomer individualCustomer = this.individualCustomerDao.findById(id).get();
		individualCustomer.setDeleted(true);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.individualCustomerDeleted);
	}

	@Override
	public DataResult<IndividualCustomerDto> getById(int id) {
		IndividualCustomer individualCustomer = this.individualCustomerDao.findById(id).get();
		IndividualCustomerDto individualCustomerDto = this.modelMapperService.forDto().map(individualCustomer, IndividualCustomerDto.class);
		
		return new SuccessDataResult<IndividualCustomerDto>(individualCustomerDto);
	}
}
