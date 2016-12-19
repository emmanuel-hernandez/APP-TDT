package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.model.api.impl.helper.QueryHelper;
import com.efe13.tdt.enums.StatusResultService;
import com.efe13.tdt.helper.ServiceRequest;
import com.efe13.tdt.helper.ServiceResult;
import com.efe13.tdt.model.dto.PopulationDTO;
import com.efe13.tdt.service.PopulationService;

public class PopulationServiceImpl extends PopulationService {
	
	private final static Logger log = Logger.getLogger( PopulationServiceImpl.class );
	
	private ServiceResult<PopulationDTO> serviceResult = null;
	private String resultMessage;
	private StatusResultService statusResultService;
	
	public ServiceResult<PopulationDTO> getById( PopulationDTO populationDTO ) {
		try {
			serviceResult = new ServiceResult<>();
			
			populationDTO = super.getById( populationDTO );
			if( populationDTO != null ) {
				resultMessage = null;
				serviceResult.setObject( populationDTO );
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "La población especificada no existe";
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

	public ServiceResult<PopulationDTO> listAll( QueryHelper serviceRequest ) {
		try {
			serviceResult = new ServiceResult<>();
			
			ArrayList<PopulationDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll( serviceRequest ) ) {
				dtos.add( (PopulationDTO) dto );
			}
			
			PopulationDTO defaultPopulation = new PopulationDTO();
			defaultPopulation.setId( -1 );
			defaultPopulation.setName( "Seleccionar..." );
			dtos.add( 0, defaultPopulation );
			
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

	public ServiceResult<PopulationDTO> savePopulation(PopulationDTO populationDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( populationDTO );
			if( super.save( populationDTO ) > 0 ) {
				resultMessage = "La población se ha guardado correctamente";
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "No se pudo guardar la población";
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

	public ServiceResult<PopulationDTO> update(PopulationDTO populationDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( populationDTO );
			if( super.update( populationDTO ) ) {
				resultMessage = "La población se ha actualizado correctamente";
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "No se pudo actualizar la población";;
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

	public ServiceResult<PopulationDTO> delete(PopulationDTO populationDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			if( super.delete( populationDTO ) ) {
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
		PopulationDTO populationDto = (PopulationDTO) dto;
		
		if( populationDto.getName() == null || populationDto.getName().trim().isEmpty() ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
		if( populationDto.getState() == null ) {
			throw new ValidationException( "El campo estado es requerido" );
		}
	}
}