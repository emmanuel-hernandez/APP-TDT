package com.efe13.tdt.service;

import java.util.ArrayList;
import java.util.List;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.dao.PopulationDAO;
import com.efe13.tdt.model.dto.PopulationDTO;
import com.efe13.tdt.model.entity.Population;

public class PopulationService extends ServiceAPI {
	
	private static final PopulationDAO dao = new PopulationDAO();
	
	@Override
	public PopulationDTO getById( DTOAPI dto ) {
		Population entity = new Population();
		
		try {
			entity = (Population) map( dto, entity );
			entity = dao.getById( entity );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
		
		if( entity == null )
			return new PopulationDTO();
		
		return (PopulationDTO) map( entity, dto );	
	}

	@Override
	public List<DTOAPI> getAll() {
		List<EntityAPI> entities = dao.getAll();
		
		ArrayList<DTOAPI> dtos = new ArrayList<>();
		if( !entities.isEmpty() ) {
			for( EntityAPI entity : entities ) {
				dtos.add( (PopulationDTO) map( entity, new PopulationDTO() ) );
			}			
		}
		
		return dtos;
	}
	
	
}