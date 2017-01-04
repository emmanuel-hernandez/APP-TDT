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
import com.efe13.tdt.dao.ConcessionaireDAO;
import com.efe13.tdt.model.dto.ConcessionaireDTO;
import com.efe13.tdt.model.entity.Concessionaire;

public class ConcessionaireService extends ServiceAPI {
	
	private static final Logger log = Logger.getLogger( ConcessionaireService.class );
	private static final ConcessionaireDAO CONCESSIONAIRE_DAO = new ConcessionaireDAO();
	
	@Override
	public long getTableCount() {
		try {
			return CONCESSIONAIRE_DAO.getTableCount();
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}
	
	@Override
	public ConcessionaireDTO getById( DTOAPI dto ) {
		Concessionaire entity = new Concessionaire();
		
		try {
			entity = (Concessionaire) map( dto, entity );
			entity = (Concessionaire) CONCESSIONAIRE_DAO.getById( entity );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
		}
		
		if( entity == null )
			return null;
		
		return (ConcessionaireDTO) map( entity, dto );	
	}

	@Override
	public <E> List<DTOAPI> getAll( E queryHelper ) {
		List<DTOAPI> dtos = Collections.emptyList();
		
		try {
			List<EntityAPI> entities = CONCESSIONAIRE_DAO.getAll( queryHelper );
			if( !entities.isEmpty() ) {
				dtos = new ArrayList<>();
				
				for( EntityAPI entity : entities ) {
					dtos.add( (ConcessionaireDTO) map( entity, new ConcessionaireDTO() ) );
				}			
			}
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
		}
		
		return dtos;
	}

	public short findByName( DTOAPI concessionaireDTO ) {
		Concessionaire entity = new Concessionaire();
		short id = 0;
		
		try {
			entity = (Concessionaire) map( concessionaireDTO, entity );
			id = CONCESSIONAIRE_DAO.findByName( entity );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
		
		return id;
	}
	
	@Override
	public Short save(DTOAPI concessionaireDTO) {
		try {
			Concessionaire concessionaire = (Concessionaire) map( concessionaireDTO, new Concessionaire() );
			return (short) CONCESSIONAIRE_DAO.save( concessionaire );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Boolean update(DTOAPI concessionaireDTO) {
		try {
			Concessionaire concessionaire = (Concessionaire) map( concessionaireDTO, new Concessionaire() );
			return CONCESSIONAIRE_DAO.update( concessionaire );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Boolean delete(DTOAPI concessionaireDTO) {
		try {
			concessionaireDTO.setActive( ActiveEnum.INACTIVE.getValue() );
			return update( concessionaireDTO );
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