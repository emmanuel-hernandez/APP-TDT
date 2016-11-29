package com.efe13.tdt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.model.dto.ConcessionTypeDTO;
import com.efe13.tdt.service.ConcessionTypeService;

/**
 * @author Emmanuel
 *
 */
@RestController
@RequestMapping( "/concessionType" )
public class ConcessionTypeController {
	
	private final static ServiceAPI service = new ConcessionTypeService();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public List<DTOAPI> getConcessionTypes() {
		return service.getAll();
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public DTOAPI getPopultation( @PathVariable("id") short populationId ) {
		ConcessionTypeDTO population = new ConcessionTypeDTO();
		population.setId( populationId );
		
		return service.getById( population );
	}
}