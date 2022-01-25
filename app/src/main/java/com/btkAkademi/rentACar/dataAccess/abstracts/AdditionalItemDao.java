package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.AdditionalItem;

public interface AdditionalItemDao extends JpaRepository<AdditionalItem, Integer>{
	List<AdditionalItem> findAllByRentalId(int rentalId);
}
