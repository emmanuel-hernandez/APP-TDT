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
import com.efe13.tdt.dao.StateDAO;
import com.efe13.tdt.model.dto.StateDTO;
import com.efe13.tdt.model.entity.State;

public class StateService extends ServiceAPI {
	
	private static final Logger log = Logger.getLogger( StateService.class );
	private static final StateDAO STATE_DAO = new StateDAO();
	
	@Override
	public StateDTO getById(DTOAPI stateDTO) {
		State entity = new State();
		
		try {
			entity = (State) map( stateDTO, entity );
			entity = STATE_DAO.getById( entity );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
		
		if( entity == null )
			return null;
		
		return (StateDTO) map( entity, stateDTO );	
	}

	@Override
	public <E> List<DTOAPI> getAll(E queryHelper) {
		List<DTOAPI> dtos = Collections.emptyList();
		
		try {
			List<EntityAPI> entities = STATE_DAO.getAll( queryHelper );
			if( !entities.isEmpty() ) {
				dtos = new ArrayList<>();
				for( EntityAPI state : entities ) {
					dtos.add( (StateDTO) map( state, new StateDTO() ) );
				}
			}

			return dtos;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Short save(DTOAPI stateDTO) {
		try {
			State state = (State) map( stateDTO, new State() );
			return (short) STATE_DAO.save( state );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Boolean update(DTOAPI stateDTO) {
		try {
			State state = (State) map( stateDTO, new State() );
			return STATE_DAO.update( state );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Boolean delete(DTOAPI stateDTO) {
		try {
			stateDTO.setActive( ActiveEnum.INACTIVE.getValue() );
			return update( stateDTO );
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