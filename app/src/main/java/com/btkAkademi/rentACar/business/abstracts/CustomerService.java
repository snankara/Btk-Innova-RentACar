package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.entities.concretes.Customer;

public interface CustomerService {
	DataResult<Customer> getById(int customerId);
}
