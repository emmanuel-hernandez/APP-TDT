package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.tdt.model.dto.ChannelDTO;
import com.efe13.tdt.service.ChannelService;

public class ChannelServiceImpl extends ChannelService {
	
	public ChannelDTO getById( ChannelDTO channelDto ) throws RuntimeException {
		channelDto = super.getById( channelDto );
		if( channelDto == null )
			throw new NullPointerException( "El canal especificado no existe" );
		
		return channelDto;
	}

	public ArrayList<ChannelDTO> listAll() {
		ArrayList<ChannelDTO> dtos = new ArrayList<>();
		for( DTOAPI dto : super.getAll() ) {
			dtos.add( (ChannelDTO) dto );
		}

		return dtos;
	}

	public short save(ChannelDTO channelDto) {
		validateDTO( channelDto );
		return super.save( channelDto );
	}

	public boolean update(ChannelDTO channelDto) {
		validateDTO( channelDto );
		return super.update( channelDto );
	}

	public boolean delete(ChannelDTO channelDto) {
		return super.delete( channelDto );
	}
	
	@Override
	public void validateDTO( DTOAPI dto ) {
		ChannelDTO channelDto = (ChannelDTO) dto;

		if( channelDto.getDistinctive() == null || channelDto.getDistinctive().trim().isEmpty() ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
		if( channelDto.getName() == null || channelDto.getName().trim().isEmpty() ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
		if( channelDto.getVirtualChannel() < 0 ) {
			throw new ValidationException( "El campo canal virtual es requerido" );
		}
		if( channelDto.getPhysicChannel() < 0 ) {
			throw new ValidationException( "El campo canal virtual es requerido" );
		}
		if( channelDto.getPower() < 0 ) {
			throw new ValidationException( "El campo potencia es requerido" );
		}
		if( channelDto.getAcesli() < 0 ) {
			throw new ValidationException( "El campo ACESLI es requerido" );
		}
		if( channelDto.getLongitude() == null || channelDto.getLongitude().trim().isEmpty() ) {
			throw new ValidationException( "El campo longitud es requerido" );
		}
		if( channelDto.getLatitude() == null || channelDto.getLatitude().trim().isEmpty() ) {
			throw new ValidationException( "El campo latitude es requerido" );
		}
		if( channelDto.getEffectiveDateStart() == null || channelDto.getEffectiveDateStart().trim().isEmpty() ) {
			throw new ValidationException( "El campo fecha de vigencia inicial es requerido" );
		}
		if( channelDto.getEffectiveDateEnd() == null || channelDto.getEffectiveDateEnd().trim().isEmpty() ) {
			throw new ValidationException( "El campo fecha de vigencia final es requerido" );
		}
		if( channelDto.getChannelBand() == null ) {
			throw new ValidationException( "El campo banda es requerido" );
		}
		if( channelDto.getPopulation() == null ) {
			throw new ValidationException( "El campo población es requerido" );
		}
		if( channelDto.getConcessionaire() == null ) {
			throw new ValidationException( "El campo concesionario es requerido" );
		}
		if( channelDto.getConcessionType() == null ) {
			throw new ValidationException( "El campo tipo de concesión es requerido" );
		}
	}
}