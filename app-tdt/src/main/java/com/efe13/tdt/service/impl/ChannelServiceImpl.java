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
import com.efe13.tdt.model.dto.ChannelDTO;
import com.efe13.tdt.model.dto.ConcessionTypeDTO;
import com.efe13.tdt.model.dto.ConcessionaireDTO;
import com.efe13.tdt.model.dto.PopulationDTO;
import com.efe13.tdt.service.ChannelService;

public class ChannelServiceImpl extends ChannelService {
	
	private final static Logger log = Logger.getLogger( ChannelServiceImpl.class );
	
	private ServiceResult<ChannelDTO> serviceResult = null;
	private String resultMessage;
	private StatusResultService statusResultService;
	
	private static final int FIELD_MIN_LENGTH = 3;
	private static final int DISTINCTIVE_FIELD_MAX_LENGTH = 8;
	private static final int NAME_FIELD_MAX_LENGTH = 50;
	private static final int LONGITUDE_FIELD_MAX_LENGTH = 15;
	private static final int LATITUDE_FIELD_MAX_LENGTH = 15;
	private static final int EFFECTIVE_DATE_START_FIELD_MAX_LENGTH = 60;
	private static final int EFFECTIVE_DATE_END_FIELD_MAX_LENGTH = 60;

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

	public ServiceResult<ChannelDTO> listAll( QueryHelper queryHelper ) {
		try {
			serviceResult = new ServiceResult<>();
			
			ArrayList<ChannelDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll( queryHelper ) ) {
				dtos.add( (ChannelDTO) dto );
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
	
	public ServiceResult<ChannelDTO> getByPopulation( PopulationDTO populationDTO ) {
		try {
			serviceResult = new ServiceResult<>();
			
			ArrayList<ChannelDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getByPopulation( populationDTO ) ) {
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
	
	public ServiceResult<ChannelDTO> getByConcessionaire( ConcessionaireDTO concessionaireDTO ) {
		try {
			serviceResult = new ServiceResult<>();
			
			ArrayList<ChannelDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getByConcessionaire( concessionaireDTO ) ) {
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
	
	public ServiceResult<ChannelDTO> getByConcessionType( ConcessionTypeDTO concessionTypeDTO ) {
		try {
			serviceResult = new ServiceResult<>();
			
			ArrayList<ChannelDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getByConcessionType( concessionTypeDTO ) ) {
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
			
			validateDTO( channelDTO, UpdateEnum.IS_NOT_UPDATE );
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
			
			validateDTO( channelDTO, UpdateEnum.IS_UPDATE );
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
	public void validateDTO( DTOAPI dto, UpdateEnum update ) {
		ChannelDTO channelDto = (ChannelDTO) sanitizeDTO( dto );

		//Validate empty fields
		if( Utils.isEmpty( channelDto.getDistinctive() ) ) {
			throw new ValidationException( "El campo distintivo es requerido" );
		}
		if( Utils.isEmpty( channelDto.getName() ) ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
		if( Utils.isNegative( channelDto.getVirtualChannel() ) ) {
			throw new ValidationException( "El campo canal virtual es requerido" );
		}
		if( Utils.isNegative( channelDto.getPhysicChannel() ) ) {
			throw new ValidationException( "El campo canal fisíco es requerido" );
		}
		if( Utils.isNegative( channelDto.getPower() ) ) {
			throw new ValidationException( "El campo potencia es requerido" );
		}
		if( Utils.isNegative( channelDto.getAcesli() ) ) {
			throw new ValidationException( "El campo ACESLI es requerido" );
		}
		if( Utils.isEmpty( channelDto.getLongitude() ) ) {
			throw new ValidationException( "El campo longitud es requerido" );
		}
		if( Utils.isEmpty( channelDto.getLatitude() ) ) {
			throw new ValidationException( "El campo latitude es requerido" );
		}
		/*if( Utils.isEmpty( channelDto.getEffectiveDateStart() ) ) {
			throw new ValidationException( "El campo fecha de vigencia inicial es requerido" );
		}
		if( Utils.isEmpty( channelDto.getEffectiveDateEnd() ) ) {
			throw new ValidationException( "El campo fecha de vigencia final es requerido" );
		}*/
		if( Utils.isNull( channelDto.getChannelBand() ) || Utils.isNegative( channelDto.getChannelBand().getId() ) ) {
			throw new ValidationException( "El campo banda es requerido" );
		}
		if( Utils.isNull( channelDto.getPopulation() ) || Utils.isNegative( channelDto.getPopulation().getId() ) ) {
			throw new ValidationException( "El campo población es requerido" );
		}
		if( Utils.isNull( channelDto.getConcessionaire() ) || Utils.isNegative( channelDto.getConcessionaire().getId() ) ) {
			throw new ValidationException( "El campo concesionario es requerido" );
		}
		if( Utils.isNull( channelDto.getConcessionType() ) || Utils.isNegative( channelDto.getConcessionType().getId() ) ) {
			throw new ValidationException( "El campo tipo de concesión es requerido" );
		}
		
		//Validate fields length
		int lengthCheck = Utils.lengthCheck( channelDto.getDistinctive(), FIELD_MIN_LENGTH,  DISTINCTIVE_FIELD_MAX_LENGTH ); 
		String exceptionMessage = "El campo distintivo es demasiado" + (( lengthCheck < 0 ) ? " corto" : " largo");
		if( lengthCheck != 0 ) {
			throw new ValidationException( exceptionMessage );
		}
		
		lengthCheck = Utils.lengthCheck( channelDto.getName(), FIELD_MIN_LENGTH, NAME_FIELD_MAX_LENGTH );
		exceptionMessage = "El campo nombre es demasiado" + (( lengthCheck < 0 ) ? " corto" : " largo");
		if( lengthCheck != 0 ) {
			throw new ValidationException( exceptionMessage );
		}
		
		lengthCheck = Utils.lengthCheck( channelDto.getName(), FIELD_MIN_LENGTH, LATITUDE_FIELD_MAX_LENGTH );
		exceptionMessage = "El campo latitud es demasiado" + (( lengthCheck < 0 ) ? " corto" : " largo");
		if( lengthCheck != 0 ) {
			throw new ValidationException( exceptionMessage );
		}
		
		lengthCheck = Utils.lengthCheck( channelDto.getName(), FIELD_MIN_LENGTH, LONGITUDE_FIELD_MAX_LENGTH );
		exceptionMessage = "El campo longitud es demasiado" + (( lengthCheck < 0 ) ? " corto" : " largo");
		if( lengthCheck != 0 ) {
			throw new ValidationException( exceptionMessage );
		}
		/*
		lengthCheck = Utils.lengthCheck( channelDto.getName(), FIELD_MIN_LENGTH, EFFECTIVE_DATE_START_FIELD_MAX_LENGTH );
		exceptionMessage = "El campo fecha de vigencia inicial es demasiado" + (( lengthCheck < 0 ) ? " corto" : " largo");
		if( lengthCheck != 0 ) {
			throw new ValidationException( exceptionMessage );
		}
		
		lengthCheck = Utils.lengthCheck( channelDto.getName(), FIELD_MIN_LENGTH, EFFECTIVE_DATE_END_FIELD_MAX_LENGTH );
		exceptionMessage = "El campo fecha de vigencia inicial es demasiado" + (( lengthCheck < 0 ) ? " corto" : " largo");
		if( lengthCheck != 0 ) {
			throw new ValidationException( exceptionMessage );
		}
		*/
		
		//Validate repeated
		short idFound = super.findByDistinctive( channelDto );
		if( idFound > 0 ) {
			exceptionMessage = "Ya existe un canal con el mismo distintivo";
			if( update == UpdateEnum.IS_NOT_UPDATE ) {
				throw new ValidationException( exceptionMessage );
			}
			if( update == UpdateEnum.IS_UPDATE && ( idFound != channelDto.getId() ) ) {
				throw new ValidationException( exceptionMessage );
			}
		}
		
		idFound = super.findByName( channelDto );
		if( idFound > 0 ) {
			exceptionMessage = "Ya existe un canal con el mismo nombre";
			if( update == UpdateEnum.IS_NOT_UPDATE ) {
				throw new ValidationException( exceptionMessage );
			}
			if( update == UpdateEnum.IS_UPDATE && ( idFound != channelDto.getId() ) ) {
				throw new ValidationException( exceptionMessage );
			}
		}
		
		idFound = super.findByPhysicChannel( channelDto );
		if( idFound > 0 ) {
			exceptionMessage = "El canal físico ya se encuentra asignado en esa población";
			if( update == UpdateEnum.IS_NOT_UPDATE ) {
				throw new ValidationException( exceptionMessage );
			}
			if( update == UpdateEnum.IS_UPDATE && ( idFound != channelDto.getId() ) ) {
				throw new ValidationException( exceptionMessage );
			}
		}
		
		idFound = super.findByVirtualChannel( channelDto );
		if( idFound > 0 ) {
			exceptionMessage = "El canal virtual ya se encuentra asignado en esa población";
			if( update == UpdateEnum.IS_NOT_UPDATE ) {
				throw new ValidationException( exceptionMessage );
			}
			if( update == UpdateEnum.IS_UPDATE && ( idFound != channelDto.getId() ) ) {
				throw new ValidationException( exceptionMessage );
			}
		}
	}

	@Override
	public DTOAPI sanitizeDTO(DTOAPI dto) {
		ChannelDTO channelDTO = (ChannelDTO) dto;

		channelDTO.setDistinctive( Utils.toUpperCase( channelDTO.getDistinctive() ) );
		channelDTO.setName( Utils.toUpperCase( channelDTO.getName() ) );
		channelDTO.setQuality( Utils.toUpperCase( channelDTO.getQuality() ) );
		channelDTO.setResolution( Utils.toUpperCase( channelDTO.getResolution() ) );
		
		return channelDTO;
	}
}