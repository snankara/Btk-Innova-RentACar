package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.invoiceDtos.InvoiceListDto;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface InvoiceService {
	DataResult<List<InvoiceListDto>> getAll();
	Result add(CreateInvoiceRequest createInvoiceRequest);
}
