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
import com.efe13.tdt.model.dto.ConcessionaireDTO;
import com.efe13.tdt.service.ConcessionaireService;

public class ConcessionaireServiceImpl extends ConcessionaireService {
	
	private final static Logger log = Logger.getLogger( ConcessionaireServiceImpl.class );
	
	private ServiceResult<ConcessionaireDTO> serviceResult = null;
	private String resultMessage;
	private StatusResultService statusResultService;
	
	private static final int FIELD_MIN_LENGTH = 3;
	private static final int NAME_FIELD_MAX_LENGTH = 100;
	
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

	public ServiceResult<ConcessionaireDTO> listAll( QueryHelper queryHelper ) {
		try {
			serviceResult = new ServiceResult<>();
			
			ArrayList<ConcessionaireDTO> dtos = new ArrayList<>();
			for( DTOAPI dto : super.getAll( queryHelper ) ) {
				dtos.add( (ConcessionaireDTO) dto );
			}
			
			ConcessionaireDTO defaultConcessionaire = new ConcessionaireDTO();
			defaultConcessionaire.setId( -1 );
			defaultConcessionaire.setName( "Seleccionar..." );
			dtos.add( 0, defaultConcessionaire );
			
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

	public ServiceResult<ConcessionaireDTO> saveConcessionaire(ConcessionaireDTO concessionaireDTO) {
		try {
			serviceResult = new ServiceResult<>();
			
			validateDTO( concessionaireDTO, UpdateEnum.IS_NOT_UPDATE );
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
			
			validateDTO( concessionaireDTO, UpdateEnum.IS_UPDATE );
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
	public void validateDTO( DTOAPI dto, UpdateEnum update ) {
		ConcessionaireDTO concessionaireDto = (ConcessionaireDTO) sanitizeDTO( dto );

		//Validate empty fields
		if( Utils.isEmpty( concessionaireDto.getName() ) ) {
			throw new ValidationException( "El campo nombre es requerido" );
		}
		
		//Validate fields length
		int lengthCheck = Utils.lengthCheck( concessionaireDto.getName(), FIELD_MIN_LENGTH,  NAME_FIELD_MAX_LENGTH ); 
		String exceptionMessage = "El campo nombre es demasiado" + (( lengthCheck < 0 ) ? " corto" : " largo");
		if( lengthCheck != 0 ) {
			throw new ValidationException( exceptionMessage );
		}
		
		//Validate repeated
		short idFound = super.findByName( concessionaireDto );
		if( idFound > 0 ) {
			exceptionMessage = "Ya existe una concesionaria con el mismo nombre";
			if( update == UpdateEnum.IS_NOT_UPDATE ) {
				throw new ValidationException( exceptionMessage );
			}
			if( update == UpdateEnum.IS_UPDATE && ( idFound != concessionaireDto.getId() ) ) {
				throw new ValidationException( exceptionMessage );
			}
		}
	}

	@Override
	public DTOAPI sanitizeDTO(DTOAPI dto) {
		ConcessionaireDTO concessionaireDTO = (ConcessionaireDTO) dto;

		concessionaireDTO.setName( Utils.toUpperCase( concessionaireDTO.getName() ) );
		
		return concessionaireDTO;
	}
}