package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.tdt.model.dto.ConcessionTypeDTO;
import com.efe13.tdt.service.ConcessionTypeService;

public class ConcessionTypeServiceImpl extends ConcessionTypeService {
	
	public ConcessionTypeDTO getById( ConcessionTypeDTO concessionTypeDto ) throws RuntimeException {
		concessionTypeDto = super.getById( concessionTypeDto );
		if( concessionTypeDto == null ) {
			throw new NullPointerException( "La concesión especificada no existe" );
		}

		return concessionTypeDto;
	}

	public ArrayList<ConcessionTypeDTO> listAll() {
		ArrayList<ConcessionTypeDTO> dtos = new ArrayList<>();
		for( DTOAPI dto : super.getAll() ) {
			dtos.add( (ConcessionTypeDTO) dto );
		}

		return dtos;
	}

	public short save(ConcessionTypeDTO concessionTypeDto) {
		validateDTO( concessionTypeDto );
		return super.save( concessionTypeDto );
	}

	public boolean update(ConcessionTypeDTO concessionTypeDto) {
		validateDTO( concessionTypeDto );
		return super.update( concessionTypeDto );
	}

	public boolean delete(ConcessionTypeDTO concessionTypeDto) {
		return super.delete( concessionTypeDto );
	}
	
	@Override
	public void validateDTO( DTOAPI dto ) {
		ConcessionTypeDTO concessionTypeDto = (ConcessionTypeDTO) dto;

		if( concessionTypeDto.getType() == null || concessionTypeDto.getType().trim().isEmpty() ) {
			throw new ValidationException( "El campo tipo es requerido" );
		}
		if( concessionTypeDto.getDescription() == null || concessionTypeDto.getDescription().trim().isEmpty() ) {
			throw new ValidationException( "El campo descripción es requerido" );
		}
	}
}