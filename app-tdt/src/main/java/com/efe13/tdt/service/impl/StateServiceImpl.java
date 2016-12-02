package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.tdt.model.dto.StateDTO;
import com.efe13.tdt.service.StateService;

public class StateServiceImpl extends StateService {
	
	private static final Logger log = Logger.getLogger( StateServiceImpl.class );
	
	public StateDTO getById( StateDTO stateDTO ) {
		try {
			stateDTO = super.getById( stateDTO );
			if( stateDTO == null )
				throw new NullPointerException( "El estado especificado no existe" );
			
			return stateDTO;
		}
		catch( ValidationException ex ) {
			throw ex;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al obtener el estado" );
		}
	}

	public ArrayList<StateDTO> listAll() {
		try {
			ArrayList<StateDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll() )
				dtos.add( (StateDTO) dto );

			return dtos;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al obtener los estados" );
		}
	}

	public short saveState(StateDTO stateDTO) {
		try {
			validateDTO( stateDTO );
			return super.save( stateDTO );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al guardar el estado" );
		}
	}

	public boolean update(StateDTO stateDTO) {
		try {
			validateDTO( stateDTO );
			return super.update( stateDTO );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al actualizar el estado" );
		}
	}

	public boolean delete(StateDTO stateDTO) {
		try {
			return super.delete( stateDTO );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al eliminar el estado" );
		}
	}
	
	@Override
	public void validateDTO( DTOAPI dto ) {
		StateDTO stateDto = (StateDTO) dto;
		
		if( stateDto.getName() == null || stateDto.getName().trim().isEmpty() ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
		if( stateDto.getShortName() == null || stateDto.getShortName().trim().isEmpty() ) {
			throw new ValidationException( "El campo abreviatura es requerido" );
		}
	}
}