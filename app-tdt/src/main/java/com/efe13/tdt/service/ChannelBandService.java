package com.efe13.tdt.service;

import java.util.ArrayList;
import java.util.List;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.dao.ChannelBandDAO;
import com.efe13.tdt.model.dto.ChannelBandDTO;
import com.efe13.tdt.model.entity.ChannelBand;

public class ChannelBandService extends ServiceAPI {
	
	private static final ChannelBandDAO dao = new ChannelBandDAO();
	
	@Override
	public ChannelBandDTO getById( DTOAPI dto ) {
		ChannelBand entity = new ChannelBand();
		
		try {
			entity = (ChannelBand) map( dto, entity );
			entity = dao.getById( entity );
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
		List<EntityAPI> entities = dao.getAll();
		
		ArrayList<DTOAPI> dtos = new ArrayList<>();
		if( !entities.isEmpty() ) {
			for( EntityAPI entity : entities ) {
				dtos.add( (ChannelBandDTO) map( entity, new ChannelBandDTO() ) );
			}			
		}
		
		return dtos;
	}
	
	
}