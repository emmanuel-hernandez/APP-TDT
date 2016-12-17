package com.efe13.tdt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.model.api.impl.helper.QueryHelper;
import com.efe13.tdt.enums.StatusResultService;
import com.efe13.tdt.helper.ServiceResult;
import com.efe13.tdt.model.dto.ConcessionTypeDTO;
import com.efe13.tdt.service.impl.ConcessionTypeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Emmanuel
 *
 */
@RestController
@CrossOrigin
@RequestMapping( "/concessionType" )
public class ConcessionTypeController {
	
	private final static ConcessionTypeServiceImpl CONCESSION_TYPE_SERVICE = new ConcessionTypeServiceImpl();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public ServiceResult<ConcessionTypeDTO> getConcessionTypes( @RequestParam(name="queryHelper", required=false) String serviceRequestString ) {
		try {
			QueryHelper queryHelper = null;
			
			if( serviceRequestString != null ) {
				queryHelper = new ObjectMapper().readValue( serviceRequestString, QueryHelper.class );
			}
			
			return CONCESSION_TYPE_SERVICE.listAll( queryHelper );
		}
		catch( Exception ex ) {
			return new ServiceResult<ConcessionTypeDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public ServiceResult<ConcessionTypeDTO> getConcessionType( @PathVariable("id") short concessionTypeId ) {
		try {
			ConcessionTypeDTO concessionTypeDTO = new ConcessionTypeDTO();
			concessionTypeDTO.setId( concessionTypeId );
			return CONCESSION_TYPE_SERVICE.getById( concessionTypeDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ConcessionTypeDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/", method=RequestMethod.POST )
	public ServiceResult<ConcessionTypeDTO> saveConcessionType( @RequestBody ConcessionTypeDTO concessionTypeDTO ) {
		try {
			concessionTypeDTO.setActive( ActiveEnum.ACTIVE.getValue() );
			return CONCESSION_TYPE_SERVICE.saveConcessionType( concessionTypeDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ConcessionTypeDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.PUT )
	public ServiceResult<ConcessionTypeDTO> updateConcessionType( @PathVariable("id") short concessionTypeId, @RequestBody ConcessionTypeDTO concessionTypeDTO ) {
		try {
			ServiceResult<ConcessionTypeDTO> serviceResult = getConcessionType( concessionTypeId );
			if( serviceResult.getStatusResult() == StatusResultService.STATUS_SUCCESS ) {
				serviceResult.getObject().setId( concessionTypeId );
				return CONCESSION_TYPE_SERVICE.update( concessionTypeDTO );
			}
			
			return serviceResult;
		}
		catch( Exception ex ) {
			return new ServiceResult<ConcessionTypeDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.DELETE )
	public ServiceResult<ConcessionTypeDTO> deleteConcessionType( @PathVariable("id") short concessionTypeId ) {
		try {
			ServiceResult<ConcessionTypeDTO> serviceResult = getConcessionType( concessionTypeId );
			if( serviceResult.getStatusResult() == StatusResultService.STATUS_SUCCESS ) {
				return CONCESSION_TYPE_SERVICE.delete( serviceResult.getObject() );	
			}
			
			return serviceResult;
		}
		catch( Exception ex ) {
			return new ServiceResult<ConcessionTypeDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
}