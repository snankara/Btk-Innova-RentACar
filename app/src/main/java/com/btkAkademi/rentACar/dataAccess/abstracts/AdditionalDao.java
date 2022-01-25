package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Additional;

public interface AdditionalDao extends JpaRepository<Additional, Integer>{
	Additional findById(int id);
	List<Additional> findAllByIsDeletedFalse();
	List<Additional> findAllByIsDeletedTrue();
	//Additional findByRentalId(int rentalId);
}
