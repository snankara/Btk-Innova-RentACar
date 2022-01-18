package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer, Integer>{
	List<IndividualCustomer> findAllByIsDeletedFalse();
	List<IndividualCustomer> findAllByIsDeletedTrue();
	IndividualCustomer findByEmail(String email);
}
