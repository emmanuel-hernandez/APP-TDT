package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.tdt.model.dto.ChannelBandDTO;
import com.efe13.tdt.service.ChannelBandService;

public class ChannelBandServiceImpl extends ChannelBandService {
	
	public ChannelBandDTO getById( ChannelBandDTO channelBandDto ) {
		channelBandDto = super.getById( channelBandDto );
		if( channelBandDto == null )
			throw new NullPointerException( "La banda especificada no existe" );
		
		return channelBandDto;
	}

	public ArrayList<ChannelBandDTO> listAll() {
		ArrayList<ChannelBandDTO> dtos = new ArrayList<>();
		for( DTOAPI dto : super.getAll() ) {
			dtos.add( (ChannelBandDTO) dto );
		}

		return dtos;
	}

	public short save(ChannelBandDTO channelBandDto) {
		validateDTO( channelBandDto );
		return super.save( channelBandDto );
	}

	public boolean update(ChannelBandDTO channelBandDto) {
		validateDTO( channelBandDto );
		return super.update( channelBandDto );
	}

	public boolean delete(ChannelBandDTO channelBandDto) {
		return super.delete( channelBandDto );
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