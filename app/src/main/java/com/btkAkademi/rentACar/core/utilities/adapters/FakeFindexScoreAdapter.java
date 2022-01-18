package com.btkAkademi.rentACar.core.utilities.adapters;

import org.springframework.stereotype.Service;


import com.btkAkademi.rentACar.core.utilities.abstracts.FindexScoreService;
import com.btkAkademi.rentACar.core.utilities.fakeServices.FakeFindexScoreService;

@Service
public class FakeFindexScoreAdapter implements FindexScoreService{

	@Override
	public int calculateIndividualCustomerFindexScore(String nationaliltyNumber) {
		FakeFindexScoreService findexScoreService = new FakeFindexScoreService();
		var resultFindex = findexScoreService.calculateIndividualCustomerFindexScore(nationaliltyNumber);
		return resultFindex;
	}

	@Override
	public int calculateCorporateCustomerFindexScore(String textNumber) {
		FakeFindexScoreService findexScoreService = new FakeFindexScoreService();
		var resultFindex = findexScoreService.calculateCorporateCustomerFindexScore(textNumber);
		return resultFindex;

	}

}
