package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer, Integer> {
	List<CorporateCustomer> findAllByIsDeletedFalse();
	List<CorporateCustomer> findAllByIsDeletedTrue();
	CorporateCustomer findByCompanyName(String companyName);
}
