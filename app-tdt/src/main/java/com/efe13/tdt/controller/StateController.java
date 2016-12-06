package com.efe13.tdt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efe13.tdt.enums.StatusResultService;
import com.efe13.tdt.model.dto.StateDTO;
import com.efe13.tdt.service.impl.StateServiceImpl;
import com.efe13.tdt.util.ServiceResult;
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
	public ServiceResult<StateDTO> getStates() {
		try {
			return STATE_SERVICE.listAll();
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
	public ServiceResult<StateDTO> saveState( @RequestParam("state") String jsonState ) {
		try {
			StateDTO stateDTO = new ObjectMapper().readValue( jsonState, StateDTO.class );
			return STATE_SERVICE.saveState( stateDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<StateDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/", method=RequestMethod.PUT )
	public ServiceResult<StateDTO> updateState( @RequestParam("state") String jsonState ) {
		try {
			StateDTO stateDTO = new ObjectMapper().readValue( jsonState, StateDTO.class );
			return STATE_SERVICE.update( stateDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<StateDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
	
	@RequestMapping( value="/", method=RequestMethod.DELETE )
	public ServiceResult<StateDTO> deleteState( @RequestParam("state") String jsonState ) {
		try {
			StateDTO stateDTO = new ObjectMapper().readValue( jsonState, StateDTO.class );
			return STATE_SERVICE.delete( stateDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<StateDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
}