package com.btkAkademi.rentACar.core.utilities.adapters;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.core.utilities.abstracts.FakePosService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.core.utilities.services.IsBankService;
import com.btkAkademi.rentACar.entities.concretes.CreditCard;

@Service
public class FakePosAdapter implements FakePosService{

	@Override
	public Result validateCreditCard(CreditCard creditCard) {
		
		IsBankService isBankService = new IsBankService();
		
		if (isBankService.payment(creditCard.getCCv(), creditCard.getExpirationDate(), creditCard.getCardNumber(), creditCard.getFirstNameAndLastName())) {
			return new SuccessResult("Ödeme Başarılı !");
		}
		
		return new ErrorResult("Kart Bilgileri Doğrulanmadı !");
		
	}

}
