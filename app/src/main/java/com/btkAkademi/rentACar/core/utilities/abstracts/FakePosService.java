package com.btkAkademi.rentACar.core.utilities.abstracts;

import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.CreditCard;

public interface FakePosService {
	Result validateCreditCard(CreditCard creditCard);
}
