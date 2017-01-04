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
import com.efe13.tdt.dao.PopulationDAO;
import com.efe13.tdt.model.dto.PopulationDTO;
import com.efe13.tdt.model.entity.Population;
import com.efe13.tdt.model.entity.State;

public class PopulationService extends ServiceAPI {
	
	private static final Logger log = Logger.getLogger( PopulationService.class );
	private static final PopulationDAO POPULATION_DAO = new PopulationDAO();
	
	@Override
	public long getTableCount() {
		try {
			return POPULATION_DAO.getTableCount();
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}
	
	@Override
	public PopulationDTO getById( DTOAPI populationDTO ) {
		Population entity = new Population();
		
		try {
			entity = (Population) map( populationDTO, entity );
			entity = (Population) POPULATION_DAO.getById( entity );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
		
		if( entity == null )
			return null;
		
		return (PopulationDTO) map( entity, populationDTO );	
	}

	@Override
	public <E> List<DTOAPI> getAll( E queryHelper ) {
		List<DTOAPI> dtos = Collections.emptyList();;
		
		try {
			List<EntityAPI> entities = POPULATION_DAO.getAll( queryHelper );
			if( !entities.isEmpty() ) {
				dtos = new ArrayList<>();
				
				for( EntityAPI population : entities ) {
					dtos.add( (PopulationDTO) map( population, new PopulationDTO() ) );
				}
			}
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
		}
		
		return dtos;
	}
	
	public <E> List<DTOAPI> getByState( DTOAPI stateDTO ) {
		List<DTOAPI> dtos = Collections.emptyList();;
		
		try {
			State entity = new State();
			entity = (State) map( stateDTO, new State() );
			List<EntityAPI> entities = POPULATION_DAO.getByState( entity );
			if( !entities.isEmpty() ) {
				dtos = new ArrayList<>();
				
				for( EntityAPI population : entities ) {
					dtos.add( (PopulationDTO) map( population, new PopulationDTO() ) );
				}
			}
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
		}
		
		return dtos;
	}

	public short findByNameAndState( DTOAPI populationDTO ) {
		Population entity = new Population();
		short id = 0;
		
		try {
			entity = (Population) map( populationDTO, entity );
			id = POPULATION_DAO.findByNameAndState( entity );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
		
		return id;
	}
	
	@Override
	public Short save(DTOAPI populationDTO) {
		try {
			Population population = (Population) map( populationDTO, new Population() );
			return (short) POPULATION_DAO.save( population );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Boolean update(DTOAPI populationDTO) {
		try {
			Population population = (Population) map( populationDTO, new Population() );
			return POPULATION_DAO.update( population );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Boolean delete(DTOAPI populationDTO) {
		try {
			populationDTO.setActive( ActiveEnum.INACTIVE.getValue() );
			return update( populationDTO );
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