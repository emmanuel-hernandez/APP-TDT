package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.tdt.model.dto.ConcessionTypeDTO;
import com.efe13.tdt.service.ConcessionTypeService;

public class ConcessionTypeServiceImpl extends ConcessionTypeService {
	
	private static final Logger log = Logger.getLogger( ConcessionTypeServiceImpl.class );
	
	public ConcessionTypeDTO getById( ConcessionTypeDTO concessionTypeDto ) {
		try {
			concessionTypeDto = super.getById( concessionTypeDto );
			if( concessionTypeDto == null )
				throw new NullPointerException( "El estado especificado no existe" );
			
			return concessionTypeDto;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al obtener el estado" );
		}
	}

	public ArrayList<ConcessionTypeDTO> listAll() {
		try {
			ArrayList<ConcessionTypeDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll() )
				dtos.add( (ConcessionTypeDTO) dto );

			return dtos;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al obtener los estados" );
		}
	}

	public short save(ConcessionTypeDTO concessionTypeDto) {
		try {
			validateDTO( concessionTypeDto );
			return super.save( concessionTypeDto );
		}
		catch( ValidationException ex ) {
			throw ex;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al guardar el estado" );
		}
	}

	public boolean update(ConcessionTypeDTO concessionTypeDto) {
		try {
			validateDTO( concessionTypeDto );
			return super.update( concessionTypeDto );
		}
		catch( ValidationException ex ) {
			throw ex;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al actualizar el estado" );
		}
	}

	public boolean delete(ConcessionTypeDTO concessionTypeDto) {
		try {
			return super.delete( concessionTypeDto );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al eliminar el estado" );
		}
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