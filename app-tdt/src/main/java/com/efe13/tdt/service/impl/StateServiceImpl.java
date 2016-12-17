package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;

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
			
			validateDTO( stateDTO );
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
			
			validateDTO( stateDTO );
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
	public void validateDTO( DTOAPI dto ) {
		StateDTO stateDto = (StateDTO) dto;
		
		if( stateDto.getName() == null || stateDto.getName().trim().isEmpty() ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
		if( stateDto.getShortName() == null || stateDto.getShortName().trim().isEmpty() ) {
			throw new ValidationException( "El campo abreviatura es requerido" );
		}
	}
}