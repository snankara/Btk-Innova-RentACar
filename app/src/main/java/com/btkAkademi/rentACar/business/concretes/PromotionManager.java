package com.btkAkademi.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.PromotionService;
import com.btkAkademi.rentACar.business.dtos.promotionDtos.PromotionDto;
import com.btkAkademi.rentACar.business.requests.promotionRequests.CreatePromotionRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PromotionDao;
import com.btkAkademi.rentACar.entities.concretes.Promotion;

@Service
public class PromotionManager implements PromotionService{
	
	private PromotionDao promotionDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public PromotionManager(PromotionDao promotionDao, ModelMapperService modelMapperService) {
		this.promotionDao = promotionDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreatePromotionRequest createPromotionRequest) {
		Promotion promotion = this.modelMapperService.forRequest().map(createPromotionRequest, Promotion.class);
		this.promotionDao.save(promotion);
		
		return new SuccessResult();
	}

	@Override
	public DataResult<PromotionDto> getByPromotionCode(String promotionCode) {
		
		if (promotionCode != null) {
			Promotion promotion = this.promotionDao.findByPromotionCode(promotionCode);
			
			PromotionDto promotionDto = this.modelMapperService.forDto().map(promotion, PromotionDto.class);
			return new SuccessDataResult<PromotionDto>(promotionDto);
		}
		
		return new ErrorDataResult<PromotionDto>("Promotion Code Not Found");
	}
}
