package com.efe13.tdt.service;

import java.util.ArrayList;
import java.util.List;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.dao.StateDAO;
import com.efe13.tdt.model.dto.StateDTO;
import com.efe13.tdt.model.entity.State;

public class StateService extends ServiceAPI {
	
	private static final StateDAO stateDAO = new StateDAO();
	
	@Override
	public StateDTO getById( DTOAPI stateDTO ) {
		State entity = new State();
		
		try {
			entity = (State) map( stateDTO, entity );
			entity = stateDAO.getById( entity );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
		
		if( entity == null )
			return new StateDTO();
		
		return (StateDTO) map( entity, stateDTO );	
	}

	@Override
	public List<DTOAPI> getAll() {
		List<EntityAPI> entities = stateDAO.getAll();
		
		ArrayList<DTOAPI> dtos = new ArrayList<>();
		if( !entities.isEmpty() ) {
			for( EntityAPI state : entities ) {
				dtos.add( (StateDTO) map( state, new StateDTO() ) );
			}			
		}
		
		return dtos;
	}
	
	
}