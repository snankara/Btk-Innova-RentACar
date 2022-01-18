package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Promotion;

public interface PromotionDao extends JpaRepository<Promotion, Integer>{
	Promotion findByPromotionCode(String promotionCode);
}
