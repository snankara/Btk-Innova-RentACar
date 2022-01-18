package com.btkAkademi.rentACar.core.utilities.abstracts;

public interface FindexScoreService {
	int calculateIndividualCustomerFindexScore(String nationaliltyNumber);
	int calculateCorporateCustomerFindexScore(String textNumber);
}
