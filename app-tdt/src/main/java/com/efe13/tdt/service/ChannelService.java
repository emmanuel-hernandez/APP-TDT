package com.efe13.tdt.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.commons.api.exception.ValidationException;
import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.dao.ChannelDAO;
import com.efe13.tdt.model.dto.ChannelDTO;
import com.efe13.tdt.model.entity.Channel;

public class ChannelService extends ServiceAPI {
	
	private static final Logger log = Logger.getLogger( ChannelService.class );
	private static final ChannelDAO CHANNEL_DAO = new ChannelDAO();
	
	@Override
	public ChannelDTO getById( DTOAPI dto ) {
		Channel entity = new Channel();
		
		try {
			entity = (Channel) map( dto, entity );
			entity = CHANNEL_DAO.getById( entity );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
		
		if( entity == null )
			return new ChannelDTO();
		
		return (ChannelDTO) map( entity, dto );	
	}

	@Override
	public List<DTOAPI> getAll() {
		List<EntityAPI> entities = CHANNEL_DAO.getAll();
		
		ArrayList<DTOAPI> dtos = new ArrayList<>();
		if( !entities.isEmpty() ) {
			for( EntityAPI entity : entities ) {
				dtos.add( (ChannelDTO) map( entity, new ChannelDTO() ) );
			}			
		}
		
		return dtos;
	}

	@Override
	public Short save(DTOAPI channelDTO) {
		try {
			Channel channel = (Channel) map( channelDTO, new Channel() );
			return (short) CHANNEL_DAO.save( channel );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Boolean update(DTOAPI channelDTO) {
		try {
			Channel channel = (Channel) map( channelDTO, new Channel() );
			return CHANNEL_DAO.update( channel );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Boolean delete(DTOAPI channelDTO) {
		try {
			channelDTO.setActive( ActiveEnum.INACTIVE.getValue() );
			return update( channelDTO );
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