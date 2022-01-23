package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.ClassTypeService;
import com.btkAkademi.rentACar.business.dtos.classTypeDtos.ClassTypeListDto;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.ClassTypeDao;
import com.btkAkademi.rentACar.entities.concretes.ClassType;

@Service
public class ClassTypeManager implements ClassTypeService{
	private ClassTypeDao classTypeDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public ClassTypeManager(ClassTypeDao classTypeDao, ModelMapperService modelMapperService) {
		this.classTypeDao = classTypeDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ClassTypeListDto>> getAll() {
		List<ClassType> classTypes = this.classTypeDao.findAll();
		
		List<ClassTypeListDto> response = classTypes.stream().map(classType -> this.modelMapperService.forDto()
				.map(classType, ClassTypeListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ClassTypeListDto>>(response);
	}
}
