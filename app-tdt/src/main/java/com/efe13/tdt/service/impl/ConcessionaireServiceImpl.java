package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.tdt.model.dto.ConcessionaireDTO;
import com.efe13.tdt.service.ConcessionaireService;

public class ConcessionaireServiceImpl extends ConcessionaireService {
	
	private static final Logger log = Logger.getLogger( ConcessionaireServiceImpl.class );
	
	public ConcessionaireDTO getById( ConcessionaireDTO concessionaireDto ) throws RuntimeException {
		concessionaireDto = super.getById( concessionaireDto );
		if( concessionaireDto == null ) {
			throw new NullPointerException( "La concesionaria especificada no existe" );
		}

		return concessionaireDto;
	}

	public ArrayList<ConcessionaireDTO> listAll() {
		try {
			ArrayList<ConcessionaireDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll() ) {
				dtos.add( (ConcessionaireDTO) dto );
			}

			return dtos;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al obtener las concesiones" );
		}
	}

	public short save(ConcessionaireDTO concessionaireDto) {
		try {
			validateDTO( concessionaireDto );
			return super.save( concessionaireDto );
		}
		catch( ValidationException ex ) {
			throw ex;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al guardar la concesionaria" );
		}
	}

	public boolean update(ConcessionaireDTO concessionaireDto) {
		try {
			validateDTO( concessionaireDto );
			return super.update( concessionaireDto );
		}
		catch( ValidationException ex ) {
			throw ex;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al actualizar la concesionaria" );
		}
	}

	public boolean delete(ConcessionaireDTO concessionaireDto) {
		try {
			return super.delete( concessionaireDto );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al eliminar la concesionaria" );
		}
	}
	
	@Override
	public void validateDTO( DTOAPI dto ) {
		ConcessionaireDTO concessionaireDto = (ConcessionaireDTO) dto;

		if( concessionaireDto.getName() == null || concessionaireDto.getName().trim().isEmpty() ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
	}
}