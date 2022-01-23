package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.colorDtos.ColorDto;
import com.btkAkademi.rentACar.business.dtos.colorDtos.ColorListDto;
import com.btkAkademi.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.btkAkademi.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface ColorService {
	DataResult<List<ColorListDto>> getAll();
	DataResult<List<ColorListDto>> getAllIsDeletedFalse();
	DataResult<ColorDto> getById(int colorId);
	Result add(CreateColorRequest createColorRequest);
	Result update(UpdateColorRequest updateColorRequest);
	Result delete(int id);
}
