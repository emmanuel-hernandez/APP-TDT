package com.efe13.tdt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.commons.api.exception.ValidationException;
import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.dao.ChannelBandDAO;
import com.efe13.tdt.model.dto.ChannelBandDTO;
import com.efe13.tdt.model.entity.ChannelBand;

public class ChannelBandService extends ServiceAPI {
	
	private static final Logger log = Logger.getLogger( ChannelBandService.class );
	private static final ChannelBandDAO CHANNEL_BAND_DAO = new ChannelBandDAO();
	
	@Override
	public ChannelBandDTO getById( DTOAPI dto ) {
		ChannelBand entity = new ChannelBand();
		
		try {
			entity = (ChannelBand) map( dto, entity );
			entity = CHANNEL_BAND_DAO.getById( entity );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
		}
		
		if( entity == null )
			return null;
		
		return (ChannelBandDTO) map( entity, dto );	
	}

	@Override
	public List<DTOAPI> getAll() {
		List<DTOAPI> dtos = Collections.emptyList();
		
		try {
			List<EntityAPI> entities = CHANNEL_BAND_DAO.getAll();
			if( !entities.isEmpty() ) {
				dtos = new ArrayList<>();
				
				for( EntityAPI entity : entities ) {
					dtos.add( (ChannelBandDTO) map( entity, new ChannelBandDTO() ) );
				}			
			}
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
		}
		
		return dtos;
	}

	@Override
	public Short save(DTOAPI channelBandDTO) {
		try {
			ChannelBand channelBand = (ChannelBand) map( channelBandDTO, new ChannelBand() );
			return (short) CHANNEL_BAND_DAO.save( channelBand );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Boolean update(DTOAPI channelBandDTO) {
		try {
			ChannelBand channelBand = (ChannelBand) map( channelBandDTO, new ChannelBand() );
			return CHANNEL_BAND_DAO.update( channelBand );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Boolean delete(DTOAPI channelBandDTO) {
		try {
			channelBandDTO.setActive( ActiveEnum.INACTIVE.getValue() );
			return update( channelBandDTO );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public void validateDTO(DTOAPI dto) throws ValidationException {
		throw new ValidationException( "This method has not implementation. It needs to be implemented by the concrete class" );
	}
}