package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Car;

public interface CarDao extends JpaRepository<Car, Integer>{
	List<Car> findAllByIsDeletedFalse();
	List<Car> findAllByIsDeletedTrue();
	Car findTop1ByClassTypeIdAndRentalStateIsFalse(int classTypeId);
	Car findById(int carId);
}
