package com.efe13.tdt.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.commons.api.exception.ValidationException;
import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.dao.ConcessionTypeDAO;
import com.efe13.tdt.model.dto.ConcessionTypeDTO;
import com.efe13.tdt.model.entity.ConcessionType;
import com.efe13.tdt.model.entity.Population;

public class ConcessionTypeService extends ServiceAPI {
	
	private static final Logger log = Logger.getLogger( ConcessionTypeService.class );
	private static final ConcessionTypeDAO CONCESSION_TYPE = new ConcessionTypeDAO();
	
	@Override
	public ConcessionTypeDTO getById( DTOAPI dto ) {
		ConcessionType entity = new ConcessionType();
		
		try {
			entity = (ConcessionType) map( dto, entity );
			entity = CONCESSION_TYPE.getById( entity );
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
		List<EntityAPI> entities = CONCESSION_TYPE.getAll();
		
		ArrayList<DTOAPI> dtos = new ArrayList<>();
		if( !entities.isEmpty() ) {
			for( EntityAPI entity : entities ) {
				dtos.add( (ConcessionTypeDTO) map( entity, new ConcessionTypeDTO() ) );
			}			
		}
		
		return dtos;
	}

	@Override
	public Short save(DTOAPI concessionTypeDTO) {
		try {
			Population concessionType = (Population) map( concessionTypeDTO, new Population() );
			return (short) CONCESSION_TYPE.save( concessionType );
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			throw ex;
		}
	}

	@Override
	public Boolean update(DTOAPI concessionTypeDTO) {
		try {
			Population concessionType = (Population) map( concessionTypeDTO, new Population() );
			return CONCESSION_TYPE.update( concessionType );
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
	public void validateDTO(DTOAPI dto) throws ValidationException {
		throw new ValidationException( "This method has not implementation. It needs to be implemented by the concrete class" );
	}
}