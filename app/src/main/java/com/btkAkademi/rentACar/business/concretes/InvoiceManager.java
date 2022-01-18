package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.dtos.invoiceDtos.InvoiceListDto;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.InvoiceDao;
import com.btkAkademi.rentACar.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService{
	
	private ModelMapperService modelMapperService;
	private InvoiceDao invoiceDao;
	
	@Autowired
	public InvoiceManager(ModelMapperService modelMapperService, InvoiceDao invoiceDao) {
		this.modelMapperService = modelMapperService;
		this.invoiceDao = invoiceDao;
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		this.invoiceDao.save(invoice);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<InvoiceListDto>> getAll() {
		List<Invoice> invoices = this.invoiceDao.findAll();
		
		List<InvoiceListDto> invoiceListDtos = invoices.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<InvoiceListDto>>(invoiceListDtos);
	}
}
