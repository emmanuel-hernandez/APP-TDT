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
import com.efe13.tdt.model.dto.StateDTO;
import com.efe13.tdt.service.impl.StateServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Emmanuel
 *
 */
@RestController
@CrossOrigin
@RequestMapping( "/state" )
public class StateController {
	
	private final static StateServiceImpl STATE_SERVICE = new StateServiceImpl();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public ServiceResult<StateDTO> getStates( @RequestParam(name="queryHelper", required=false) String serviceRequestString ) {
		try {
			QueryHelper queryHelper = null;
			
			if( serviceRequestString != null ) {
				queryHelper = new ObjectMapper().readValue( serviceRequestString, QueryHelper.class );
			}
			
			return STATE_SERVICE.listAll( queryHelper );
		}
		catch( Exception ex ) {
			return new ServiceResult<StateDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public ServiceResult<StateDTO> getState( @PathVariable("id") short stateId ) {
		try {
			StateDTO stateDTO = new StateDTO();
			stateDTO.setId( stateId );
			return STATE_SERVICE.getById( stateDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<StateDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/", method=RequestMethod.POST )
	public ServiceResult<StateDTO> saveState( @RequestBody StateDTO stateDTO ) {
		try {
			stateDTO.setActive( ActiveEnum.ACTIVE.getValue() );
			return STATE_SERVICE.saveState( stateDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<StateDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.PUT )
	public ServiceResult<StateDTO> updateState( @PathVariable("id") short stateId, @RequestBody StateDTO stateDTO ) {
		try {
			ServiceResult<StateDTO> serviceResult = getState( stateId );
			if( serviceResult.getStatusResult() == StatusResultService.STATUS_SUCCESS ) {
				stateDTO.setId( stateId );
				return STATE_SERVICE.update( stateDTO );
			}
			
			return serviceResult;
		}
		catch( Exception ex ) {
			return new ServiceResult<StateDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.DELETE )
	public ServiceResult<StateDTO> deleteState( @PathVariable("id") short stateId ) {
		try {
			ServiceResult<StateDTO> serviceResult = getState( stateId );
			if( serviceResult.getStatusResult() == StatusResultService.STATUS_SUCCESS ) {
				return STATE_SERVICE.delete( serviceResult.getObject() );
			}
			
			return serviceResult;
		}
		catch( Exception ex ) {
			return new ServiceResult<StateDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
}