package com.btkAkademi.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CustomerDao;
import com.btkAkademi.rentACar.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService{
	private CustomerDao customerDao;

	@Autowired
	public CustomerManager(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public DataResult<Customer> getById(int customerId) {
		return new SuccessDataResult<Customer>(this.customerDao.findById(customerId));
	}
}
