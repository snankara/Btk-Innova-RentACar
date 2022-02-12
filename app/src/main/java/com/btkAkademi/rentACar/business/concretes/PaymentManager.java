package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalItemService;
import com.btkAkademi.rentACar.business.abstracts.AdditionalService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.PromotionService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.additionalDtos.AdditionalDto;
import com.btkAkademi.rentACar.business.dtos.additionalItemDtos.AdditionalItemListDto;
import com.btkAkademi.rentACar.business.dtos.carDtos.CarDto;
import com.btkAkademi.rentACar.business.dtos.paymentDtos.PaymentDto;
import com.btkAkademi.rentACar.business.dtos.paymentDtos.PaymentListDto;
import com.btkAkademi.rentACar.business.dtos.promotionDtos.PromotionDto;
import com.btkAkademi.rentACar.business.dtos.rentalDtos.RentalDto;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.abstracts.FakePosService;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PaymentDao;
import com.btkAkademi.rentACar.entities.concretes.CreditCard;
import com.btkAkademi.rentACar.entities.concretes.Invoice;
import com.btkAkademi.rentACar.entities.concretes.Payment;

@Service
public class PaymentManager implements PaymentService{
	private PaymentDao paymentDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	private AdditionalItemService additionalItemService;
	private AdditionalService additionalService;
	private CarService carService;
	private FakePosService fakeBankService;
	private PromotionService promotionService;
	private InvoiceService invoiceService;
	
	@Autowired
	public PaymentManager(PromotionService promotionService, FakePosService fakeBankService, CarService carService, 
			PaymentDao paymentDao, ModelMapperService modelMapperService, RentalService rentalService, 
			AdditionalItemService additionalItemService, AdditionalService additionalService, InvoiceService invoiceService) {
		
		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
		this.additionalItemService = additionalItemService;
		this.additionalService = additionalService;
		this.carService = carService;
		this.fakeBankService = fakeBankService;
		this.promotionService = promotionService;
		this.invoiceService = invoiceService;
	}

	@Override
	public DataResult<PaymentDto> add(CreatePaymentRequest createPaymentRequest, String promotionCode) {

		Result result = BusinessRules.run(checkIfCardValidate(createPaymentRequest.getCreditCard()));
		
		if(result != null) {
			return new ErrorDataResult<PaymentDto>(result.getMessage());
		}
		
		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		var calculateResult = calculate(createPaymentRequest.getRentalId(), promotionCode);
		
		payment.setPaymentAmount(calculateResult);
		payment.setPaymentDate(LocalDate.now());
		payment.setId(0);
		
		this.paymentDao.save(payment);
		
		PaymentDto paymentDto = this.modelMapperService.forDto().map(payment, PaymentDto.class);

		return new SuccessDataResult<PaymentDto>(paymentDto, "Ödemeniz Alındı !");
	}
	
	/*
	private Result createInvoice(int rentalId, int paymentId) {
		CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest();
		createInvoiceRequest.setRentalId(rentalId);
		createInvoiceRequest.setPaymentId(paymentId);
		createInvoiceRequest.setInvoiceDate(LocalDate.now());
		this.invoiceService.add(createInvoiceRequest);
		
		return new SuccessResult();
	}
	*/
	
	private double calculate(int rentalId, String promotionCode) {
		
		double totalAdditionalAmount = 0;
		double totalResult = 0;
		
		RentalDto rentalDto = this.rentalService.getById(rentalId).getData();
		
		List<AdditionalItemListDto> additionals = this.additionalItemService.findAllByRentalId(rentalId).getData();
		
		CarDto carDto = this.carService.getById(rentalDto.getCarId()).getData();
		
		Period diff = Period.between(rentalDto.getRentDate(), rentalDto.getReturnDate());

		if (!additionals.isEmpty()) {
			for (AdditionalItemListDto additional : additionals) {
				AdditionalDto additionalDto = this.additionalService.getById(additional.getAdditionalId()).getData();
				totalAdditionalAmount += additionalDto.getAdditionalAmount();
			}
		}
				
		if (promotionCode != null) {
			PromotionDto promotionDto = this.promotionService.getByPromotionCode(promotionCode).getData();
			totalResult = diff.getDays() * carDto.getDailyPrice() + totalAdditionalAmount;
			totalResult = totalResult - totalResult * promotionDto.getDiscountRate();
		}
		
		else {
			totalResult = diff.getDays() * carDto.getDailyPrice() + totalAdditionalAmount;
		}

		return totalResult;
	}
	
	private Result checkIfCardValidate(CreditCard creditCard) { 
		return this.fakeBankService.validateCreditCard(creditCard);
	}
	
	@Override
	public DataResult<List<PaymentListDto>> getAll() {
		List<Payment> payments = this.paymentDao.findAllByIsDeletedFalse();
		
		List<PaymentListDto> response = payments.stream().map(payment -> this.modelMapperService.forDto().map(payment, PaymentListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<PaymentListDto>>(response);
	}

	@Override
	public Result update(UpdatePaymentRequest updatePaymentRequest) {
		Payment payment = this.modelMapperService.forRequest().map(updatePaymentRequest, Payment.class);
		this.paymentDao.save(payment);
		return new SuccessResult(Messages.paymentUpdated);
	}

	@Override
	public Result delete(int id) {
		Payment payment = this.paymentDao.findById(id).get();
		payment.setDeleted(true);
		this.paymentDao.save(payment);
		return new SuccessResult(Messages.paymentDeleted);
	}

	@Override
	public DataResult<PaymentDto> getById(int id) {
		Payment payment = this.paymentDao.findById(id).get();
		PaymentDto paymentDto = this.modelMapperService.forDto().map(payment, PaymentDto.class);
		return new SuccessDataResult<PaymentDto>(paymentDto);
	}
	
}
