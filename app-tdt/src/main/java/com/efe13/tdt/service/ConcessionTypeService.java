package com.efe13.tdt.service;

import java.util.ArrayList;
import java.util.List;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.dao.ConcessionTypeDAO;
import com.efe13.tdt.model.dto.ConcessionTypeDTO;
import com.efe13.tdt.model.entity.ConcessionType;

public class ConcessionTypeService extends ServiceAPI {
	
	private static final ConcessionTypeDAO dao = new ConcessionTypeDAO();
	
	@Override
	public ConcessionTypeDTO getById( DTOAPI dto ) {
		ConcessionType entity = new ConcessionType();
		
		try {
			entity = (ConcessionType) map( dto, entity );
			entity = dao.getById( entity );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
		
		if( entity == null )
			return new ConcessionTypeDTO();
		
		return (ConcessionTypeDTO) map( entity, dto );	
	}

	@Override
	public List<DTOAPI> getAll() {
		List<EntityAPI> entities = dao.getAll();
		
		ArrayList<DTOAPI> dtos = new ArrayList<>();
		if( !entities.isEmpty() ) {
			for( EntityAPI entity : entities ) {
				dtos.add( (ConcessionTypeDTO) map( entity, new ConcessionTypeDTO() ) );
			}			
		}
		
		return dtos;
	}
	
	
}