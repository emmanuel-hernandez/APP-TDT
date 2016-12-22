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
import com.efe13.tdt.model.dto.StateDTO;
import com.efe13.tdt.service.StateService;

public class StateServiceImpl extends StateService {
	
	private final static Logger log = Logger.getLogger( StateServiceImpl.class );
	
	private ServiceResult<StateDTO> serviceResult = null;
	private String resultMessage;
	private StatusResultService statusResultService;

	private static final int FIELD_MIN_LENGTH = 3;
	private static final int NAME_FIELD_MAX_LENGTH = 25;
	private static final int SHORT_NAME_FIELD_MAX_LENGTH = 5;
	
	public ServiceResult<StateDTO> getById( StateDTO stateDTO ) {
		try {
			serviceResult = new ServiceResult<>();
			
			stateDTO = super.getById( stateDTO );
			if( stateDTO != null ) {
				resultMessage = null;
				serviceResult.setObject( stateDTO );
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "El estado especificado no existe";
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

	public ServiceResult<StateDTO> listAll( QueryHelper serviceRequest ) {
		try {
			serviceResult = new ServiceResult<>();
			
			ArrayList<StateDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll( serviceRequest ) ) {
				dtos.add( (StateDTO) dto );
			}
			
			StateDTO defaultState = new StateDTO();
			defaultState.setId( -1 );
			defaultState.setName( "Seleccionar..." );
			dtos.add( 0, defaultState );
			
			serviceResult.setCollection( dtos );
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

	public ServiceResult<StateDTO> saveState(StateDTO stateDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( stateDTO, UpdateEnum.IS_NOT_UPDATE );
			if( super.save( stateDTO ) > 0 ) {
				resultMessage = "El estado se ha guardado correctamente";
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "No se pudo guardar el estado";
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

	public ServiceResult<StateDTO> update(StateDTO stateDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( stateDTO, UpdateEnum.IS_UPDATE );
			if( super.update( stateDTO ) ) {
				resultMessage = "El estado se ha actualizado correctamente";
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "No se pudo actualizar el estado";;
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

	public ServiceResult<StateDTO> delete(StateDTO stateDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			if( super.delete( stateDTO ) ) {
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
		StateDTO stateDto = (StateDTO) sanitizeDTO( dto );
		
		//Validate empty fields
		if( Utils.isEmpty( stateDto.getName() ) ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
		if( stateDto.getShortName() == null || stateDto.getShortName().isEmpty() ) {
			throw new ValidationException( "El campo abreviatura es requerido" );
		}

		//Validate fields length
		int lengthCheck = Utils.lengthCheck( stateDto.getName(), FIELD_MIN_LENGTH,  NAME_FIELD_MAX_LENGTH ); 
		String exceptionMessage = "El campo nombre es demasiado" + (( lengthCheck < 0 ) ? " corto" : " largo");
		if( lengthCheck != 0 ) {
			throw new ValidationException( exceptionMessage );
		}
		
		lengthCheck = Utils.lengthCheck( stateDto.getShortName(), FIELD_MIN_LENGTH, SHORT_NAME_FIELD_MAX_LENGTH );
		exceptionMessage = "El campo abreviatura es demasiado" + (( lengthCheck < 0 ) ? " corto" : " largo");
		if( lengthCheck != 0 ) {
			throw new ValidationException( exceptionMessage );
		}
		
		//Validate repeated
		short idFound = super.findByName( stateDto );
		if( idFound > 0 ) {
			exceptionMessage = "Ya existe un estado con el mismo nombre";
			if( update == UpdateEnum.IS_NOT_UPDATE ) {
				throw new ValidationException( exceptionMessage );
			}
			if( update == UpdateEnum.IS_UPDATE && ( idFound != stateDto.getId() ) ) {
				throw new ValidationException( exceptionMessage );
			}
		}
		
		idFound = super.findByShortName( stateDto );
		if( idFound > 0 ) {
			exceptionMessage = "Ya existe un estado con la misma abreviatura";
			if( update == UpdateEnum.IS_NOT_UPDATE ) {
				throw new ValidationException( exceptionMessage );
			}
			if( update == UpdateEnum.IS_UPDATE && ( idFound != stateDto.getId() ) ) {
				throw new ValidationException( exceptionMessage );
			}
		}
	}
	
	@Override
	public DTOAPI sanitizeDTO(DTOAPI dto) {
		StateDTO stateDto = (StateDTO) dto;

		stateDto.setName( Utils.toUpperCase( stateDto.getName() ) );
		stateDto.setShortName( Utils.toUpperCase( stateDto.getShortName() ) );
		
		return stateDto;
	}
}