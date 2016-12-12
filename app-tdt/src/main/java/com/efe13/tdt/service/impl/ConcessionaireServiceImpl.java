package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.tdt.enums.StatusResultService;
import com.efe13.tdt.helper.ServiceRequest;
import com.efe13.tdt.helper.ServiceResult;
import com.efe13.tdt.model.dto.ConcessionaireDTO;
import com.efe13.tdt.service.ConcessionaireService;

public class ConcessionaireServiceImpl extends ConcessionaireService {
	
	private final static Logger log = Logger.getLogger( ConcessionaireServiceImpl.class );
	
	private ServiceResult<ConcessionaireDTO> serviceResult = null;
	private String resultMessage;
	private StatusResultService statusResultService;
	
	public ServiceResult<ConcessionaireDTO> getById( ConcessionaireDTO concessionaireDTO ) {
		try {
			serviceResult = new ServiceResult<>();
			
			concessionaireDTO = super.getById( concessionaireDTO );
			if( concessionaireDTO != null ) {
				resultMessage = null;
				serviceResult.setObject( concessionaireDTO );
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "La concesionaria especificada no existe";
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

	public ServiceResult<ConcessionaireDTO> listAll( ServiceRequest serviceRequest ) {
		try {
			serviceResult = new ServiceResult<>();
			
			ArrayList<ConcessionaireDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll( serviceRequest ) ) {
				dtos.add( (ConcessionaireDTO) dto );
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

	public ServiceResult<ConcessionaireDTO> saveConcessionaire(ConcessionaireDTO concessionaireDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( concessionaireDTO );
			if( super.save( concessionaireDTO ) > 0 ) {
				resultMessage = "La concesionaria se ha guardado correctamente";
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "No se pudo guardar la concesionaria";
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

	public ServiceResult<ConcessionaireDTO> update(ConcessionaireDTO concessionaireDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( concessionaireDTO );
			if( super.update( concessionaireDTO ) ) {
				resultMessage = "La concesionaria se ha actualizado correctamente";
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "No se pudo actualizar la concesionaria";;
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

	public ServiceResult<ConcessionaireDTO> delete(ConcessionaireDTO concessionaireDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			if( super.delete( concessionaireDTO ) ) {
				resultMessage = "La concesionaria se ha eliminado correctamente";
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "No se pudo eliminar la concesionaria";;
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
		ConcessionaireDTO concessionaireDto = (ConcessionaireDTO) dto;

		if( concessionaireDto.getName() == null || concessionaireDto.getName().trim().isEmpty() ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
	}
}