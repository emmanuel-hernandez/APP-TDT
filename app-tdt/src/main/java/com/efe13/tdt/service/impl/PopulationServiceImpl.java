package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.tdt.model.dto.PopulationDTO;
import com.efe13.tdt.service.PopulationService;

public class PopulationServiceImpl extends PopulationService {
	
	public PopulationDTO getById( PopulationDTO populationDTO ) throws RuntimeException {
		populationDTO = super.getById( populationDTO );
		if( populationDTO == null ) {
			throw new NullPointerException( "La población especificada no existe" );
		}
		
		return populationDTO;
	}

	public ArrayList<PopulationDTO> listAll() {
		ArrayList<PopulationDTO> dtos = new ArrayList<>();
		for( DTOAPI dto : super.getAll() ) {
			dtos.add( (PopulationDTO) dto );
		}
		
		return dtos;
	}

	public short save(PopulationDTO populationDTO) {
		validateDTO( populationDTO );
		return super.save( populationDTO );
	}

	public boolean update(PopulationDTO populationDTO) {
		validateDTO( populationDTO );
		return super.update( populationDTO );
	}

	public boolean delete(PopulationDTO populationDTO) {
		return super.delete( populationDTO );
	}
	
	@Override
	public void validateDTO( DTOAPI dto ) {
		PopulationDTO populationDto = (PopulationDTO) dto;
		
		if( populationDto.getName() == null || populationDto.getName().trim().isEmpty() ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
		if( populationDto.getState() == null ) {
			throw new ValidationException( "El campo estado es requerido" );
		}
	}
}