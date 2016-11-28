package com.efe13.tdt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.tdt.model.dto.StateDTO;
import com.efe13.tdt.service.StateService;
import com.efe13.tdt.service.impl.StateServiceImpl;

/**
 * @author Emmanuel
 *
 */
@RestController
@RequestMapping( "/state" )
public class EstadoController {
	
	private final static StateService stateService = new StateServiceImpl();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public List<DTOAPI> getStates() {
		return stateService.getAll();
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public StateDTO getState( @PathVariable("id") short stateId ) {
		StateDTO state = new StateDTO();
		state.setStateId( stateId );
		
		return stateService.getById( state );
	}
}