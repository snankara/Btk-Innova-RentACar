package com.btkAkademi.rentACar.core.utilities.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperManager implements ModelMapperService{

	private ModelMapper modelMapper;
	
	@Autowired
	public ModelMapperManager(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public ModelMapper forDto() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);//bütün alanların olmak zorunda olmadığını anlatmak için gevşek bağlı bir strateji olduğunu söyledik. Loose, gevşek bağlılık demektir.
		return modelMapper;
	}

	@Override
	public ModelMapper forRequest() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);//request dto'dan gelen bütün alanların entity ile eşleşmesi için standart strateji olduğunu söyledik.
		return modelMapper;
	}

}
