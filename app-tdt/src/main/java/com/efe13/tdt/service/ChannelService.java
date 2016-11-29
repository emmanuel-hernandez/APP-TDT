package com.efe13.tdt.service;

import java.util.ArrayList;
import java.util.List;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.dao.ChannelDAO;
import com.efe13.tdt.model.dto.ChannelDTO;
import com.efe13.tdt.model.entity.Channel;

public class ChannelService extends ServiceAPI {
	
	private static final ChannelDAO dao = new ChannelDAO();
	
	@Override
	public ChannelDTO getById( DTOAPI dto ) {
		Channel entity = new Channel();
		
		try {
			entity = (Channel) map( dto, entity );
			entity = dao.getById( entity );
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
		List<EntityAPI> entities = dao.getAll();
		
		ArrayList<DTOAPI> dtos = new ArrayList<>();
		if( !entities.isEmpty() ) {
			for( EntityAPI entity : entities ) {
				dtos.add( (ChannelDTO) map( entity, new ChannelDTO() ) );
			}			
		}
		
		return dtos;
	}
	
	
}