package com.efe13.tdt.service.impl;

import java.util.ArrayList;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.tdt.enums.StatusResultService;
import com.efe13.tdt.model.dto.ChannelDTO;
import com.efe13.tdt.service.ChannelService;
import com.efe13.tdt.util.ServiceResult;

public class ChannelServiceImpl extends ChannelService {
	
	private final static Logger log = Logger.getLogger( ChannelServiceImpl.class );
	
	private ServiceResult<ChannelDTO> serviceResult = null;
	private String resultMessage;
	private StatusResultService statusResultService;
	
	public ServiceResult<ChannelDTO> getById( ChannelDTO channelDTO ) {
		try {
			serviceResult = new ServiceResult<>();
			
			channelDTO = super.getById( channelDTO );
			if( channelDTO != null ) {
				resultMessage = null;
				serviceResult.setObject( channelDTO );
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

	public ServiceResult<ChannelDTO> listAll() {
		try {
			serviceResult = new ServiceResult<>();
			
			ArrayList<ChannelDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll() ) {
				dtos.add( (ChannelDTO) dto );
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

	public ServiceResult<ChannelDTO> saveChannel(ChannelDTO channelDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( channelDTO );
			if( super.save( channelDTO ) > 0 ) {
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

	public ServiceResult<ChannelDTO> update(ChannelDTO channelDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( channelDTO );
			if( super.update( channelDTO ) ) {
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

	public ServiceResult<ChannelDTO> delete(ChannelDTO channelDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			if( super.delete( channelDTO ) ) {
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
		ChannelDTO channelDto = (ChannelDTO) dto;

		if( channelDto.getDistinctive() == null || channelDto.getDistinctive().trim().isEmpty() ) {
			throw new ValidationException( "El campo distintivo es requerido" );
		}
		if( channelDto.getName() == null || channelDto.getName().trim().isEmpty() ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
		if( channelDto.getVirtualChannel() < 0 ) {
			throw new ValidationException( "El campo canal virtual es requerido" );
		}
		if( channelDto.getPhysicChannel() < 0 ) {
			throw new ValidationException( "El campo canal fisíco es requerido" );
		}
		if( channelDto.getPower() < 0 ) {
			throw new ValidationException( "El campo potencia es requerido" );
		}
		if( channelDto.getAcesli() < 0 ) {
			throw new ValidationException( "El campo ACESLI es requerido" );
		}
		if( channelDto.getLongitude() == null || channelDto.getLongitude().trim().isEmpty() ) {
			throw new ValidationException( "El campo longitud es requerido" );
		}
		if( channelDto.getLatitude() == null || channelDto.getLatitude().trim().isEmpty() ) {
			throw new ValidationException( "El campo latitude es requerido" );
		}
		if( channelDto.getEffectiveDateStart() == null || channelDto.getEffectiveDateStart().trim().isEmpty() ) {
			throw new ValidationException( "El campo fecha de vigencia inicial es requerido" );
		}
		if( channelDto.getEffectiveDateEnd() == null || channelDto.getEffectiveDateEnd().trim().isEmpty() ) {
			throw new ValidationException( "El campo fecha de vigencia final es requerido" );
		}
		if( channelDto.getChannelBand() == null ) {
			throw new ValidationException( "El campo banda es requerido" );
		}
		if( channelDto.getPopulation() == null ) {
			throw new ValidationException( "El campo población es requerido" );
		}
		if( channelDto.getConcessionaire() == null ) {
			throw new ValidationException( "El campo concesionario es requerido" );
		}
		if( channelDto.getConcessionType() == null ) {
			throw new ValidationException( "El campo tipo de concesión es requerido" );
		}
	}
}