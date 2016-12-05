package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.tdt.enums.StatusResultService;
import com.efe13.tdt.model.dto.ChannelBandDTO;
import com.efe13.tdt.service.ChannelBandService;
import com.efe13.tdt.util.ServiceResult;

public class ChannelBandServiceImpl extends ChannelBandService {

	private final static Logger log = Logger.getLogger( ChannelBandServiceImpl.class );
	
	private ServiceResult<ChannelBandDTO> serviceResult = null;
	private String resultMessage;
	private StatusResultService statusResultService;
	
	public ServiceResult<ChannelBandDTO> getById( ChannelBandDTO channelBandDTO ) {
		try {
			serviceResult = new ServiceResult<>();
			
			channelBandDTO = super.getById( channelBandDTO );
			if( channelBandDTO != null ) {
				resultMessage = null;
				serviceResult.setObject( channelBandDTO );
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "El canal especificada no existe";
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

	public ServiceResult<ChannelBandDTO> listAll() {
		try {
			serviceResult = new ServiceResult<>();
			
			ArrayList<ChannelBandDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll() ) {
				dtos.add( (ChannelBandDTO) dto );
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

	public ServiceResult<ChannelBandDTO> saveChannelBand(ChannelBandDTO channelBandDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( channelBandDTO );
			if( super.save( channelBandDTO ) > 0 ) {
				resultMessage = "El canal se ha guardado correctamente";
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "No se pudo guardar el canal";
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

	public ServiceResult<ChannelBandDTO> update(ChannelBandDTO channelBandDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( channelBandDTO );
			if( super.update( channelBandDTO ) ) {
				resultMessage = "El canal se ha actualizado correctamente";
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "No se pudo actualizar el canal";;
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

	public ServiceResult<ChannelBandDTO> delete(ChannelBandDTO channelBandDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			if( super.delete( channelBandDTO ) ) {
				resultMessage = "El canal se ha eliminado correctamente";
				statusResultService = StatusResultService.STATUS_SUCCESS;
			}
			else {
				resultMessage = "No se pudo eliminar el canal";;
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
		ChannelBandDTO channelBandDto = (ChannelBandDTO) dto;

		if( channelBandDto.getName() == null || channelBandDto.getName().trim().isEmpty() ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
		if( channelBandDto.getDescription() == null || channelBandDto.getDescription().trim().isEmpty() ) {
			throw new ValidationException( "El campo descripción es requerido" );
		}
	}
}