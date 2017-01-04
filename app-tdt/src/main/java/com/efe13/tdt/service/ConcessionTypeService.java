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
import com.efe13.tdt.dao.ConcessionTypeDAO;
import com.efe13.tdt.model.dto.ConcessionTypeDTO;
import com.efe13.tdt.model.entity.ConcessionType;

public class ConcessionTypeService extends ServiceAPI {
	
	private static final Logger log = Logger.getLogger( ConcessionTypeService.class );
	private static final ConcessionTypeDAO CONCESSION_TYPE_DAO = new ConcessionTypeDAO();
	
	@Override
	public long getTableCount() {
		try {
			return CONCESSION_TYPE_DAO.getTableCount();
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}
	
	@Override
	public ConcessionTypeDTO getById( DTOAPI dto ) {
		ConcessionType entity = new ConcessionType();
		
		try {
			entity = (ConcessionType) map( dto, entity );
			entity = (ConcessionType) CONCESSION_TYPE_DAO.getById( entity );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}

		if( entity == null )
			return null;

		return (ConcessionTypeDTO) map( entity, dto );	
	}

	@Override
	public <E> List<DTOAPI> getAll( E queryHelper ) {
		List<DTOAPI> dtos = Collections.emptyList();
		
		try {
			List<EntityAPI> entities = CONCESSION_TYPE_DAO.getAll( queryHelper );
			if( !entities.isEmpty() ) {
				dtos = new ArrayList<>();
				for( EntityAPI entity : entities ) {
					dtos.add( (ConcessionTypeDTO) map( entity, new ConcessionTypeDTO() ) );
				}
			}
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
		}
		
		return dtos;
	}

	public long findByName( DTOAPI concessionTypeDTO ) {
		ConcessionType entity = new ConcessionType();
		long id = 0;
		
		try {
			entity = (ConcessionType) map( concessionTypeDTO, entity );
			id = CONCESSION_TYPE_DAO.findByName( entity );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
		
		return id;
	}
	
	@Override
	public Short save(DTOAPI concessionTypeDTO) {
		try {
			ConcessionType concessionType = (ConcessionType) map( concessionTypeDTO, new ConcessionType() );
			return (short) CONCESSION_TYPE_DAO.save( concessionType );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Boolean update(DTOAPI concessionTypeDTO) {
		try {
			ConcessionType concessionType = (ConcessionType) map( concessionTypeDTO, new ConcessionType() );
			return CONCESSION_TYPE_DAO.update( concessionType );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Boolean delete(DTOAPI concessionTypeDTO) {
		try {
			concessionTypeDTO.setActive( ActiveEnum.INACTIVE.getValue() );
			return update( concessionTypeDTO );
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