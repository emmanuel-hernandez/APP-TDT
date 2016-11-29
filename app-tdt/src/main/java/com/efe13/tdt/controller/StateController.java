package com.efe13.tdt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.model.dto.StateDTO;
import com.efe13.tdt.service.StateService;

/**
 * @author Emmanuel
 *
 */
@RestController
@RequestMapping( "/state" )
public class StateController {
	
	private final static ServiceAPI service = new StateService();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public List<DTOAPI> getStates() {
		return service.getAll();
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public DTOAPI getState( @PathVariable("id") short stateId ) {
		StateDTO state = new StateDTO();
		state.setId( stateId );
		
		return service.getById( state );
	}
}