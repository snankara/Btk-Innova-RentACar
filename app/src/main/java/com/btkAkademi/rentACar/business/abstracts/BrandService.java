package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.brandDtos.BrandDto;
import com.btkAkademi.rentACar.business.dtos.brandDtos.BrandListDto;
import com.btkAkademi.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface BrandService {
	DataResult<List<BrandListDto>> getAllIsDeletedFalse();
	DataResult<List<BrandListDto>> getAll();
	DataResult<BrandDto> getById(int brandId);
	Result add(CreateBrandRequest createBrandRequest);
	Result update(UpdateBrandRequest updateBrandRequest);
	Result delete(int id);
}
