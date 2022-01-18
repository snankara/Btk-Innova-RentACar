package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.brandDtos.BrandDto;
import com.btkAkademi.rentACar.business.dtos.brandDtos.BrandListDto;
import com.btkAkademi.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.BrandDao;
import com.btkAkademi.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<BrandListDto>> getAll() {
		List<Brand> brandList =  this.brandDao.findAllByIsDeletedFalse();
		//stream api -- stream listeyi dolaşmaya (iterate etmeye) yarıyor.
		List<BrandListDto> response = brandList.stream().map(brand -> modelMapperService.forDto() //stream api kullanarak her bir brand'i dto'ya çevirdik
				.map(brand, BrandListDto.class)).collect(Collectors.toList()); // collect ile listeye çeviriyoruz.
		return new SuccessDataResult<List<BrandListDto>>(response);
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		
		Result result = BusinessRules.run(
				checkIfBrandNameExists(createBrandRequest.getName()), 
				checkIfBrandLimitExceeded(3));
		
		if(result != null) {
			return result;
		}
		
		Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult(Messages.brandAdded);

	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		
		Result result = BusinessRules.run(
				checkIfBrandNameExists(updateBrandRequest.getName()),
				checkBrandId(updateBrandRequest.getId()));
		
		if(result != null) {
			return result;
		}
		
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult(Messages.brandUpdated);
	}
	
	private Result checkBrandId(int brandId) {
		var brand = this.brandDao.findById(brandId);
		if(brand == null) {
			return new ErrorResult(Messages.brandNotFound);
		}
		
		return new SuccessResult();
	}
	
	private Result checkIfBrandNameExists(String brandName) {
		
		Brand brand = this.brandDao.findByName(brandName);
		
		if(brand != null) {
			return new ErrorResult(Messages.brandNameExists);
		}		
		return new SuccessResult();
	}
	
	private Result checkIfBrandLimitExceeded(int limit) {
		if(this.brandDao.count() >= limit) { // count datayı çekmeden datanın sayısını verir.
			return new ErrorResult(Messages.brandLimitExceeded);
		}
		return new SuccessResult();
	}

	@Override
	public DataResult<BrandDto> getById(int brandId) {
		Brand brand = this.brandDao.findById(brandId);
		BrandDto brandDto = this.modelMapperService.forDto().map(brand, BrandDto.class);
		
		return new SuccessDataResult<BrandDto>(brandDto);
	}

	@Override
	public Result delete(int id) {
		Brand brand = this.brandDao.findById(id);
		brand.setDeleted(true);
		this.brandDao.save(brand);
		return new SuccessResult(Messages.brandDeleted);
	}

}
