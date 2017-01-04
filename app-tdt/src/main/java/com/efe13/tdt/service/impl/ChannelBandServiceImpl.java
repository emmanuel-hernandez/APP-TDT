package com.efe13.tdt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;

import com.efe13.mvc.commons.api.enums.UpdateEnum;
import com.efe13.mvc.commons.api.util.Utils;
import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.model.api.impl.helper.QueryHelper;
import com.efe13.tdt.enums.StatusResultService;
import com.efe13.tdt.helper.ServiceResult;
import com.efe13.tdt.model.dto.ChannelBandDTO;
import com.efe13.tdt.service.ChannelBandService;

public class ChannelBandServiceImpl extends ChannelBandService {

	private final static Logger log = Logger.getLogger( ChannelBandServiceImpl.class );
	
	private ServiceResult<ChannelBandDTO> serviceResult = null;
	private String resultMessage;
	private StatusResultService statusResultService;
	
	private static final int FIELD_MIN_LENGTH = 2;
	private static final int NAME_FIELD_MAX_LENGTH = 3;
	private static final int DESCRIPTION_FIELD_MAX_LENGTH = 10;
	
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

	public ServiceResult<ChannelBandDTO> listAll( QueryHelper queryHelper ) {
		try {
			serviceResult = new ServiceResult<>();
			
			ArrayList<ChannelBandDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll( queryHelper ) ) {
				dtos.add( (ChannelBandDTO) dto );
			}
			
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

	public ServiceResult<ChannelBandDTO> saveChannelBand(ChannelBandDTO channelBandDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( channelBandDTO, UpdateEnum.IS_NOT_UPDATE );
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
			
			validateDTO( channelBandDTO, UpdateEnum.IS_UPDATE );
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
	public void validateDTO( DTOAPI dto, UpdateEnum update ) {
		ChannelBandDTO channelBandDto = (ChannelBandDTO) sanitizeDTO( dto );

		//Validate empty fields
		if( Utils.isEmpty( channelBandDto.getName() ) ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
		if( Utils.isEmpty( channelBandDto.getDescription() ) ) {
			throw new ValidationException( "El campo descripción es requerido" );
		}
		
		//Validate fields length
		int lengthCheck = Utils.lengthCheck( channelBandDto.getName(), FIELD_MIN_LENGTH,  NAME_FIELD_MAX_LENGTH ); 
		String exceptionMessage = "El campo nombre es demasiado" + (( lengthCheck < 0 ) ? " corto" : " largo");
		if( lengthCheck != 0 ) {
			throw new ValidationException( exceptionMessage );
		}
		
		lengthCheck = Utils.lengthCheck( channelBandDto.getDescription(), FIELD_MIN_LENGTH, DESCRIPTION_FIELD_MAX_LENGTH );
		exceptionMessage = "El campo descripción es demasiado" + (( lengthCheck < 0 ) ? " corto" : " largo");
		if( lengthCheck != 0 ) {
			throw new ValidationException( exceptionMessage );
		}
		
		//Validate repeated
		List<ChannelBandDTO> channelBandDTOs = listAll( null ).getCollection();
		for( ChannelBandDTO channelBand : channelBandDTOs ) {
			if( channelBand.isActive() ) {
				if( update.getValue() && channelBand.getId() == channelBandDto.getId() ) {
					continue;
				}
				if( channelBand.getName().compareToIgnoreCase( channelBandDto.getName() ) == 0 ) {
					throw new ValidationException( "Ya existe una banda con el mismo nombre" );
				}
			}
		}
	}

	@Override
	public DTOAPI sanitizeDTO(DTOAPI dto) {
		ChannelBandDTO channelBandDTO = (ChannelBandDTO) dto;

		channelBandDTO.setName( Utils.toUpperCase( channelBandDTO.getName() ) );
		channelBandDTO.setDescription( Utils.toUpperCase( channelBandDTO.getDescription() ) );
		
		return channelBandDTO;
	}
}