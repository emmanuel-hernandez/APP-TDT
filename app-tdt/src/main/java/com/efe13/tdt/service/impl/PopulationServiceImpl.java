package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.tdt.model.dto.PopulationDTO;
import com.efe13.tdt.service.PopulationService;

public class PopulationServiceImpl extends PopulationService {
	
	private static final Logger log = Logger.getLogger( PopulationServiceImpl.class );
	
	public PopulationDTO getById( PopulationDTO populationDTO ) {
		try {
			populationDTO = super.getById( populationDTO );
			if( populationDTO == null )
				throw new NullPointerException( "El estado especificado no existe" );
			
			return populationDTO;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al obtener el estado" );
		}
	}

	public ArrayList<PopulationDTO> listAll() {
		try {
			ArrayList<PopulationDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll() )
				dtos.add( (PopulationDTO) dto );

			return dtos;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al obtener los estados" );
		}
	}

	public short save(PopulationDTO populationDTO) {
		try {
			validateDTO( populationDTO );
			return super.save( populationDTO );
		}
		catch( ValidationException ex ) {
			throw ex;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al guardar el estado" );
		}
	}

	public boolean update(PopulationDTO populationDTO) {
		try {
			validateDTO( populationDTO );
			return super.update( populationDTO );
		}
		catch( ValidationException ex ) {
			throw ex;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al actualizar el estado" );
		}
	}

	public boolean delete(PopulationDTO populationDTO) {
		try {
			return super.delete( populationDTO );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al eliminar el estado" );
		}
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