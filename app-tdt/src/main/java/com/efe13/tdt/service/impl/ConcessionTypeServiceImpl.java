package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;

import com.efe13.mvc.commons.api.enums.UpdateEnum;
import com.efe13.mvc.commons.api.util.Utils;
import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.model.api.impl.helper.QueryHelper;
import com.efe13.tdt.enums.StatusResultService;
import com.efe13.tdt.helper.ServiceResult;
import com.efe13.tdt.model.dto.ConcessionTypeDTO;
import com.efe13.tdt.service.ConcessionTypeService;

public class ConcessionTypeServiceImpl extends ConcessionTypeService {
	
	private final static Logger log = Logger.getLogger( ConcessionTypeServiceImpl.class );
	
	private ServiceResult<ConcessionTypeDTO> serviceResult = null;
	private String resultMessage;
	private StatusResultService statusResultService;
	
	private static final int FIELD_MIN_LENGTH = 3;
	private static final int TYPE_FIELD_MAX_LENGTH = 25;
	private static final int DESCRIPTION_FIELD_MAX_LENGTH = 40;
	
	public ServiceResult<ConcessionTypeDTO> getById( ConcessionTypeDTO concessionTypeDTO ) {
		try {
			serviceResult = new ServiceResult<>();
			
			concessionTypeDTO = super.getById( concessionTypeDTO );
			if( concessionTypeDTO != null ) {
				resultMessage = null;
				serviceResult.setObject( concessionTypeDTO );
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "La concesión especificada no existe";
				statusResultService = StatusResultService.STATUS_FAILED;
			}
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			resultMessage = ex.getMessage();
			statusResultService = StatusResultService.STATUS_FAILED;
		}
		
		serviceResult.setMessage( resultMessage );
		serviceResult.setStatusResult( statusResultService );
		return serviceResult;
	}

	public ServiceResult<ConcessionTypeDTO> listAll( QueryHelper queryHelper ) {
		try {
			serviceResult = new ServiceResult<>();
			resultMessage = null;
			
			ArrayList<ConcessionTypeDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll( queryHelper ) ) {
				dtos.add( (ConcessionTypeDTO) dto );
			}
			
			ConcessionTypeDTO defaultConcessionType = new ConcessionTypeDTO();
			defaultConcessionType.setId( -1 );
			defaultConcessionType.setType( "Seleccionar..." );
			dtos.add( 0, defaultConcessionType );
			
			serviceResult.setCollection( dtos );
			if( !Utils.isNull( queryHelper ) ) {
				serviceResult.setQueryHelper( getQueryHelper( getTableCount(), queryHelper ) );
			}
			statusResultService = StatusResultService.STATUS_SUCCESS;
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			resultMessage = ex.getMessage();
			statusResultService = StatusResultService.STATUS_FAILED;
		}
		
		serviceResult.setMessage( resultMessage );
		serviceResult.setStatusResult( statusResultService );
		return serviceResult;
	}

	public ServiceResult<ConcessionTypeDTO> saveConcessionType(ConcessionTypeDTO concessionTypeDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( concessionTypeDTO, UpdateEnum.IS_NOT_UPDATE );
			if( super.save( concessionTypeDTO ) > 0 ) {
				resultMessage = "La concesión se ha guardado correctamente";
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "No se pudo guardar la concesión";
				statusResultService = StatusResultService.STATUS_FAILED;
			}
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			resultMessage = ex.getMessage();
			statusResultService = StatusResultService.STATUS_FAILED;
		}
		
		serviceResult.setMessage( resultMessage );
		serviceResult.setStatusResult( statusResultService );
		return serviceResult;
	}

	public ServiceResult<ConcessionTypeDTO> update(ConcessionTypeDTO concessionTypeDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( concessionTypeDTO, UpdateEnum.IS_UPDATE );
			if( super.update( concessionTypeDTO ) ) {
				resultMessage = "La concesión se ha actualizado correctamente";
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "No se pudo actualizar la concesión";;
				statusResultService = StatusResultService.STATUS_FAILED;
			}
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			resultMessage = ex.getMessage();
			statusResultService = StatusResultService.STATUS_FAILED;
		}
		
		serviceResult.setMessage( resultMessage );
		serviceResult.setStatusResult( statusResultService );
		return serviceResult;
	}

	public ServiceResult<ConcessionTypeDTO> delete(ConcessionTypeDTO concessionTypeDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			if( super.delete( concessionTypeDTO ) ) {
				resultMessage = "El estado se ha eliminado correctamente";
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "No se pudo eliminar el estado";;
				statusResultService = StatusResultService.STATUS_FAILED;
			}
		}
		catch( Exception ex ) {
			log.error( ex.getMessage(), ex );
			resultMessage = ex.getMessage();
			statusResultService = StatusResultService.STATUS_FAILED;
		}
		
		serviceResult.setMessage( resultMessage );
		serviceResult.setStatusResult( statusResultService );
		return serviceResult;
	}

	@Override
	public void validateDTO( DTOAPI dto, UpdateEnum update ) {
		ConcessionTypeDTO concessionTypeDto = (ConcessionTypeDTO) sanitizeDTO( dto );

		//Validate empty fields
		if( Utils.isEmpty( concessionTypeDto.getType() ) ) {
			throw new ValidationException( "El campo tipo es requerido" );
		}
		if( Utils.isEmpty( concessionTypeDto.getDescription() ) ) {
			throw new ValidationException( "El campo descripción es requerido" );
		}
		
		//Validate fields length
		int lengthCheck = Utils.lengthCheck( concessionTypeDto.getType(), FIELD_MIN_LENGTH,  TYPE_FIELD_MAX_LENGTH ); 
		String exceptionMessage = "El campo nombre es demasiado" + (( lengthCheck < 0 ) ? " corto" : " largo");
		if( lengthCheck != 0 ) {
			throw new ValidationException( exceptionMessage );
		}
		
		lengthCheck = Utils.lengthCheck( concessionTypeDto.getDescription(), FIELD_MIN_LENGTH, DESCRIPTION_FIELD_MAX_LENGTH );
		exceptionMessage = "El campo descripción es demasiado" + (( lengthCheck < 0 ) ? " corto" : " largo");
		if( lengthCheck != 0 ) {
			throw new ValidationException( exceptionMessage );
		}
		
		long idFound = super.findByName( concessionTypeDto );
		if( idFound > 0 ) {
			exceptionMessage = "Ya existe un tipo de concesión con el mismo nombre";
			if( update == UpdateEnum.IS_NOT_UPDATE ) {
				throw new ValidationException( exceptionMessage );
			}
			if( update == UpdateEnum.IS_UPDATE && ( idFound != concessionTypeDto.getId() ) ) {
				throw new ValidationException( exceptionMessage );
			}
		}
	}

	@Override
	public DTOAPI sanitizeDTO(DTOAPI dto) {
		ConcessionTypeDTO concessionTypeDTO = (ConcessionTypeDTO) dto;

		concessionTypeDTO.setType( Utils.toUpperCase( concessionTypeDTO.getType() ) );
		concessionTypeDTO.setDescription( Utils.toUpperCase( concessionTypeDTO.getDescription() ) );
		
		return concessionTypeDTO;
	}
}