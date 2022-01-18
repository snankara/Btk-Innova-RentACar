package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CreditCardService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.creditCardDtos.CreditCardDto;
import com.btkAkademi.rentACar.business.dtos.creditCardDtos.CreditCardListDto;
import com.btkAkademi.rentACar.business.requests.creditCardRequests.CreateCreditCardRequest;
import com.btkAkademi.rentACar.business.requests.creditCardRequests.UpdateCreditCardRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CreditCardDao;
import com.btkAkademi.rentACar.entities.concretes.CreditCard;

@Service
public class CreditCardManager implements CreditCardService{
	
	private CreditCardDao creditCardDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CreditCardManager(ModelMapperService modelMapperService, CreditCardDao creditCardDao) {
		this.creditCardDao = creditCardDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCreditCardRequest createCreditCardRequest) {
		CreditCard creditCard = this.modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
		this.creditCardDao.save(creditCard);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateCreditCardRequest updateCreditCardRequest) {
		CreditCard creditCard = this.modelMapperService.forRequest().map(updateCreditCardRequest, CreditCard.class);
		this.creditCardDao.save(creditCard);
		return new SuccessResult(Messages.creditCardUpdated);
	}

	@Override
	public DataResult<List<CreditCardListDto>> getAll() {
		List<CreditCard> creditCards = this.creditCardDao.findAllByIsDeletedFalse();
		
		List<CreditCardListDto> response = creditCards.stream().map(creditCard -> this.modelMapperService.forDto().map(creditCard, CreditCardListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CreditCardListDto>>(response);
	}

	@Override
	public Result delete(int id) {
		CreditCard creditCard = this.creditCardDao.findById(id).get();
		creditCard.setDeleted(true);
		this.creditCardDao.save(creditCard);
		return new SuccessResult(Messages.creditCardDeleted);
	}

	@Override
	public DataResult<CreditCardDto> getById(int id) {
		CreditCard creditCard = this.creditCardDao.findById(id).get();
		CreditCardDto creditCardDto = this.modelMapperService.forDto().map(creditCard, CreditCardDto.class);
		
		return new SuccessDataResult<CreditCardDto>(creditCardDto);
	}
}
