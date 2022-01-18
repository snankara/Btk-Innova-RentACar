package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.paymentDtos.PaymentDto;
import com.btkAkademi.rentACar.business.dtos.paymentDtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface PaymentService {
	Result add(CreatePaymentRequest createPaymentRequest, String promotionCode);
	Result update(UpdatePaymentRequest updatePaymentRequest);
	Result delete(int id);
	DataResult<PaymentDto> getById(int id);
	DataResult<List<PaymentListDto>> getAll();
}
