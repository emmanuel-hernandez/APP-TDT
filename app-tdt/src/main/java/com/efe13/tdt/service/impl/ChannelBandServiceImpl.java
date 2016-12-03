package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.tdt.model.dto.ChannelBandDTO;
import com.efe13.tdt.service.ChannelBandService;

public class ChannelBandServiceImpl extends ChannelBandService {
	
	private static final Logger log = Logger.getLogger( ChannelBandServiceImpl.class );
	
	public ChannelBandDTO getById( ChannelBandDTO channelBandDto ) throws RuntimeException {
		channelBandDto = super.getById( channelBandDto );
		if( channelBandDto == null )
			throw new NullPointerException( "La banda especificada no existe" );
		
		return channelBandDto;
	}

	public ArrayList<ChannelBandDTO> listAll() {
		try {
			ArrayList<ChannelBandDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll() ) {
				dtos.add( (ChannelBandDTO) dto );
			}

			return dtos;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al obtener los estados" );
		}
	}

	public short save(ChannelBandDTO channelBandDto) {
		try {
			validateDTO( channelBandDto );
			return super.save( channelBandDto );
		}
		catch( ValidationException ex ) {
			throw ex;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al guardar la banda" );
		}
	}

	public boolean update(ChannelBandDTO channelBandDto) {
		try {
			validateDTO( channelBandDto );
			return super.update( channelBandDto );
		}
		catch( ValidationException ex ) {
			throw ex;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al actualizar la banda" );
		}
	}

	public boolean delete(ChannelBandDTO channelBandDto) {
		try {
			return super.delete( channelBandDto );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw new RuntimeException( "Ocurrió un error al eliminar la banda" );
		}
	}
	
	@Override
	public void validateDTO( DTOAPI dto ) {
		ChannelBandDTO channelBandDto = (ChannelBandDTO) dto;

		if( channelBandDto.getName() == null || channelBandDto.getName().trim().isEmpty() ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
		if( channelBandDto.getDescription() == null || channelBandDto.getDescription().trim().isEmpty() ) {
			throw new ValidationException( "El campo descripción es requerido" );
		}
	}
}