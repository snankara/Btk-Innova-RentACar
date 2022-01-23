package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.classTypeDtos.ClassTypeListDto;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;

public interface ClassTypeService {
	DataResult<List<ClassTypeListDto>> getAll();
}
