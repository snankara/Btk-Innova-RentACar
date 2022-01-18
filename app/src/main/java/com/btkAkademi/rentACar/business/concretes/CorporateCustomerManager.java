package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.corporateCustomerDtos.CorporateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.corporateCustomerDtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService{
	
	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		Result result = BusinessRules.run(checkIfCompanyNameExists(createCorporateCustomerRequest.getCompanyName()));
		
		if(result != null) {
			return result;
		}
		
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.corporateCustomerAdded);
	}
	
	private Result checkIfCompanyNameExists(String companyName) {
		
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.findByCompanyName(companyName);
		
		if(corporateCustomer != null) {
			return new ErrorResult(Messages.companyNameExists);
		}	
		
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.corporateCustomerUpdated);
	}

	@Override
	public Result delete(int id) {
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.findById(id).get();
		corporateCustomer.setDeleted(true);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.corporateCustomerDeleted);
	}

	@Override
	public DataResult<List<CorporateCustomerListDto>> getAll() {
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAllByIsDeletedFalse();
		
		List<CorporateCustomerListDto> response = corporateCustomers.stream().map(corporateCustomer -> this.modelMapperService.forDto()
				.map(corporateCustomer, CorporateCustomerListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CorporateCustomerListDto>>(response);
	}

	@Override
	public DataResult<CorporateCustomerDto> getById(int id) {		
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.findById(id).get();
		CorporateCustomerDto corporateCustomerDto = this.modelMapperService.forDto().map(corporateCustomer, CorporateCustomerDto.class);
		
		return new SuccessDataResult<CorporateCustomerDto>(corporateCustomerDto);
	}
	
}
