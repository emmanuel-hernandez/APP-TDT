package com.efe13.tdt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.model.dto.PopulationDTO;
import com.efe13.tdt.model.dto.StateDTO;
import com.efe13.tdt.service.PopulationService;
import com.efe13.tdt.service.StateService;

/**
 * @author Emmanuel
 *
 */
@RestController
@RequestMapping( "/population" )
public class PopulationController {
	
	private final static ServiceAPI service = new PopulationService();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public List<DTOAPI> getPopulations() {
		return service.getAll();
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public DTOAPI getPopultation( @PathVariable("id") short populationId ) {
		PopulationDTO population = new PopulationDTO();
		population.setId( populationId );
		
		return service.getById( population );
	}
}