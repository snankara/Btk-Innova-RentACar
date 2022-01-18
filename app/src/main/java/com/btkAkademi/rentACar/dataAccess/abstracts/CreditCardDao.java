package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.CreditCard;

public interface CreditCardDao extends JpaRepository<CreditCard, Integer>{
	List<CreditCard> findAllByIsDeletedFalse();
	List<CreditCard> findAllByIsDeletedTrue();
}
