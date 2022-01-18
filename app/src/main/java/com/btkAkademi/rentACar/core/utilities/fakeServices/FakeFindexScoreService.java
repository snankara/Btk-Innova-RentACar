package com.btkAkademi.rentACar.core.utilities.fakeServices;

public class FakeFindexScoreService {
	public int calculateIndividualCustomerFindexScore(String natioalityNumber){
		if (natioalityNumber != null) {
			return 1000;
		}
		
		return 0;
	}
	
	public int calculateCorporateCustomerFindexScore(String textNumber){
		if (textNumber != null) {
			return 1250;
		}
		
		return 0;
	}
}
