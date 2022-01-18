package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Brand;

public interface BrandDao extends JpaRepository<Brand, Integer>{
	List<Brand> findAllByIsDeletedFalse();
	List<Brand> findAllByIsDeletedTrue();
	Brand findByName(String name); 
	Brand findById(int brandId);
}
