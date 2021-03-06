package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.ColorService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.colorDtos.ColorDto;
import com.btkAkademi.rentACar.business.dtos.colorDtos.ColorListDto;
import com.btkAkademi.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.btkAkademi.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.ColorDao;
import com.btkAkademi.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService{

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ColorListDto>> getAllIsDeletedFalse() {
		List<Color> colorList = this.colorDao.findAllByIsDeletedFalse();
		
		List<ColorListDto> response = colorList.stream().map(color -> modelMapperService.forDto()
				.map(color, ColorListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ColorListDto>>(response);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		
		Result result = BusinessRules.run(checkIfColorNameExists(createColorRequest.getName()));
		
		if(result != null) {
			return result;
		}
		
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorDao.save(color);
		return new SuccessResult(Messages.colorAdded);
	}
	
	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		
		Result result = BusinessRules.run(
				checkColorId(updateColorRequest.getId()),
				checkIfColorNameExists(updateColorRequest.getName()));
		
		if(result != null) {
			return result;
		}
		
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorDao.save(color);
		return new SuccessResult(Messages.colorUpdated);
	}
	
	private Result checkColorId(int colorId) {
		var color = this.colorDao.findById(colorId);
		if(color == null) {
			return new ErrorResult(Messages.colorNotFound);
		}
		
		return new SuccessResult();
	}
	
	private Result checkIfColorNameExists(String colorName) {
		
		Color color = this.colorDao.findByName(colorName);
		
		if(color != null) {
			return new ErrorResult(Messages.colorNameExists);
		}		
		return new SuccessResult();
	}

	@Override
	public DataResult<ColorDto> getById(int colorId) {
		Color color = this.colorDao.findById(colorId);
		ColorDto colorDto = this.modelMapperService.forDto().map(color, ColorDto.class);
		
		return new SuccessDataResult<ColorDto>(colorDto);
	}

	@Override
	public Result delete(int id) {
		Color color = this.colorDao.findById(id);
		color.setDeleted(true);
		this.colorDao.save(color);
		return new SuccessResult(Messages.colorDeleted);
	}

	@Override
	public DataResult<List<ColorListDto>> getAll() {
		List<Color> colorList = this.colorDao.findAll();
		
		List<ColorListDto> response = colorList.stream().map(color -> modelMapperService.forDto()
				.map(color, ColorListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ColorListDto>>(response);
	}
}
