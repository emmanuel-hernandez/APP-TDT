package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.tdt.model.dto.ConcessionaireDTO;
import com.efe13.tdt.service.ConcessionaireService;

public class ConcessionaireServiceImpl extends ConcessionaireService {
	
	public ConcessionaireDTO getById( ConcessionaireDTO concessionaireDto ) throws RuntimeException {
		concessionaireDto = super.getById( concessionaireDto );
		if( concessionaireDto == null ) {
			throw new NullPointerException( "La concesionaria especificada no existe" );
		}

		return concessionaireDto;
	}

	public ArrayList<ConcessionaireDTO> listAll() {
		ArrayList<ConcessionaireDTO> dtos = new ArrayList<>();
		for( DTOAPI dto : super.getAll() ) {
			dtos.add( (ConcessionaireDTO) dto );
		}

		return dtos;
	}

	public short save(ConcessionaireDTO concessionaireDto) {
		validateDTO( concessionaireDto );
		return super.save( concessionaireDto );
	}

	public boolean update(ConcessionaireDTO concessionaireDto) {
		validateDTO( concessionaireDto );
		return super.update( concessionaireDto );
	}

	public boolean delete(ConcessionaireDTO concessionaireDto) {
		return super.delete( concessionaireDto );
	}
	
	@Override
	public void validateDTO( DTOAPI dto ) {
		ConcessionaireDTO concessionaireDto = (ConcessionaireDTO) dto;

		if( concessionaireDto.getName() == null || concessionaireDto.getName().trim().isEmpty() ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
	}
}