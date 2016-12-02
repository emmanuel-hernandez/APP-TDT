package com.efe13.tdt.service;

import java.util.ArrayList;
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
			ex.printStackTrace();
		}
		
		if( entity == null )
			return new ChannelBandDTO();
		
		return (ChannelBandDTO) map( entity, dto );	
	}

	@Override
	public List<DTOAPI> getAll() {
		List<EntityAPI> entities = CHANNEL_BAND_DAO.getAll();
		
		ArrayList<DTOAPI> dtos = new ArrayList<>();
		if( !entities.isEmpty() ) {
			for( EntityAPI entity : entities ) {
				dtos.add( (ChannelBandDTO) map( entity, new ChannelBandDTO() ) );
			}			
		}
		
		return dtos;
	}

	@Override
	public Short save(DTOAPI channelBandDTO) {
		try {
			ChannelBand population = (ChannelBand) map( channelBandDTO, new ChannelBand() );
			return (short) CHANNEL_BAND_DAO.save( population );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Boolean update(DTOAPI channelBandDTO) {
		try {
			ChannelBand population = (ChannelBand) map( channelBandDTO, new ChannelBand() );
			return CHANNEL_BAND_DAO.update( population );
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