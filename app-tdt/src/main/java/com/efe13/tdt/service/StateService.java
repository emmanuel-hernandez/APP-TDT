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

	@Override
	public Short save(DTOAPI stateDTO) {
		try {
			State state = (State) map( stateDTO, new State() );
			return (short) stateDAO.save( state );
		}
		catch( Exception ex ) {
			System.out.println( "EXCEPTION ON save()!!!!" );
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public Boolean update(DTOAPI stateDTO) {
		try {
			State state = (State) map( stateDTO, new State() );
			return stateDAO.update( state );
		}
		catch( Exception ex ) {
			System.out.println( "EXCEPTION ON update()!!!!" );
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean delete(DTOAPI stateDTO) {
		try {
			State state = (State) map( stateDTO, new State() );
			return stateDAO.delete( state );
		}
		catch( Exception ex ) {
			System.out.println( "EXCEPTION ON delete()!!!!" );
			ex.printStackTrace();
			return false;
		}
	}
	
}