package com.efe13.tdt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.commons.api.enums.UpdateEnum;
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
			entity = (Channel) CHANNEL_DAO.getById( entity );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
		}
		
		if( entity == null )
			return null;
		
		return (ChannelDTO) map( entity, dto );	
	}

	@Override
	public <E> List<DTOAPI> getAll( E queryHelper ) {
		List<DTOAPI> dtos = Collections.emptyList();
		
		try {
			
			List<EntityAPI> entities = CHANNEL_DAO.getAll( queryHelper );
			if( !entities.isEmpty() ) {
				dtos = new ArrayList<>();
				
				for( EntityAPI entity : entities ) {
					dtos.add( (ChannelDTO) map( entity, new ChannelDTO() ) );
				}			
			}
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
		}
		
		return dtos;
	}

	public short findByDistinctive( DTOAPI channel ) {
		Channel entity = new Channel();
		short id = 0;
		
		try {
			entity = (Channel) map( channel, entity );
			id = CHANNEL_DAO.findByDistinctive( entity );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
		
		return id;
	}
	
	public short findByName( DTOAPI channel ) {
		Channel entity = new Channel();
		short id = 0;
		
		try {
			entity = (Channel) map( channel, entity );
			id = CHANNEL_DAO.findByName( entity );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
		
		return id;
	}
	
	public short findByPhysicChannel( DTOAPI channel ) {
		Channel entity = new Channel();
		short id = 0;
		
		try {
			entity = (Channel) map( channel, entity );
			id = CHANNEL_DAO.findByPhysicChannel( entity );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
		
		return id;
	}
	
	public short findByVirtualChannel( DTOAPI channel ) {
		Channel entity = new Channel();
		short id = 0;
		
		try {
			entity = (Channel) map( channel, entity );
			id = CHANNEL_DAO.findByVirtualChannel( entity );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
		
		return id;
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
	public void validateDTO(DTOAPI dto, UpdateEnum update) throws ValidationException {
		throw new ValidationException( "This method has not implementation. It needs to be implemented by the concrete class" );
	}

	@Override
	public DTOAPI sanitizeDTO(DTOAPI dto) throws ValidationException {
		throw new ValidationException( "This method has not implementation. It needs to be implemented by the concrete class" );	
	}
}