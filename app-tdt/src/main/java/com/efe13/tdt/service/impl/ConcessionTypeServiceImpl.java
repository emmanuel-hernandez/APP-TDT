package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;

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

	public ServiceResult<ConcessionTypeDTO> listAll( QueryHelper serviceRequest ) {
		try {
			serviceResult = new ServiceResult<>();
			
			ArrayList<ConcessionTypeDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll( serviceRequest ) ) {
				dtos.add( (ConcessionTypeDTO) dto );
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

	public ServiceResult<ConcessionTypeDTO> saveConcessionType(ConcessionTypeDTO concessionTypeDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( concessionTypeDTO );
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
			
			validateDTO( concessionTypeDTO );
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
	public void validateDTO( DTOAPI dto ) {
		ConcessionTypeDTO concessionTypeDto = (ConcessionTypeDTO) dto;

		if( concessionTypeDto.getType() == null || concessionTypeDto.getType().trim().isEmpty() ) {
			throw new ValidationException( "El campo tipo es requerido" );
		}
		if( concessionTypeDto.getDescription() == null || concessionTypeDto.getDescription().trim().isEmpty() ) {
			throw new ValidationException( "El campo descripción es requerido" );
		}
	}
}