package com.efe13.tdt.service;

import java.util.ArrayList;
import java.util.List;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.dao.ConcessionaireDAO;
import com.efe13.tdt.model.dto.ConcessionaireDTO;
import com.efe13.tdt.model.entity.Concessionaire;

public class ConcessionaireService extends ServiceAPI {
	
	private static final ConcessionaireDAO dao = new ConcessionaireDAO();
	
	@Override
	public ConcessionaireDTO getById( DTOAPI dto ) {
		Concessionaire entity = new Concessionaire();
		
		try {
			entity = (Concessionaire) map( dto, entity );
			entity = dao.getById( entity );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
		
		if( entity == null )
			return new ConcessionaireDTO();
		
		return (ConcessionaireDTO) map( entity, dto );	
	}

	@Override
	public List<DTOAPI> getAll() {
		List<EntityAPI> entities = dao.getAll();
		
		ArrayList<DTOAPI> dtos = new ArrayList<>();
		if( !entities.isEmpty() ) {
			for( EntityAPI entity : entities ) {
				dtos.add( (ConcessionaireDTO) map( entity, new ConcessionaireDTO() ) );
			}			
		}
		
		return dtos;
	}
	
	
}