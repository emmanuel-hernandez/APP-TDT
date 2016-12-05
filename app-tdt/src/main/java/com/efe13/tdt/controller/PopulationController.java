package com.efe13.tdt.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efe13.tdt.enums.StatusResultService;
import com.efe13.tdt.model.dto.PopulationDTO;
import com.efe13.tdt.service.impl.PopulationServiceImpl;
import com.efe13.tdt.util.ServiceResult;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Emmanuel
 *
 */
@RestController
@RequestMapping( "/population" )
public class PopulationController {
	
	private final static PopulationServiceImpl STATE_SERVICE = new PopulationServiceImpl();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public ServiceResult<PopulationDTO> getPopulations() {
		try {
			return STATE_SERVICE.listAll();
		}
		catch( Exception ex ) {
			return new ServiceResult<PopulationDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public ServiceResult<PopulationDTO> getPopulation( @PathVariable("id") short populationId ) {
		try {
			PopulationDTO populationDTO = new PopulationDTO();
			populationDTO.setId( populationId );
			return STATE_SERVICE.getById( populationDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<PopulationDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/", method=RequestMethod.POST )
	public ServiceResult<PopulationDTO> savePopulation( @RequestParam("population") String jsonPopulation ) {
		try {
			PopulationDTO populationDTO = new ObjectMapper().readValue( jsonPopulation, PopulationDTO.class );
			return STATE_SERVICE.savePopulation( populationDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<PopulationDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/", method=RequestMethod.PUT )
	public ServiceResult<PopulationDTO> updatePopulation( @RequestParam("population") String jsonPopulation ) {
		try {
			PopulationDTO populationDTO = new ObjectMapper().readValue( jsonPopulation, PopulationDTO.class );
			return STATE_SERVICE.update( populationDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<PopulationDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
	
	@RequestMapping( value="/", method=RequestMethod.DELETE )
	public ServiceResult<PopulationDTO> deletePopulation( @RequestParam("population") String jsonPopulation ) {
		try {
			PopulationDTO populationDTO = new ObjectMapper().readValue( jsonPopulation, PopulationDTO.class );
			return STATE_SERVICE.delete( populationDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<PopulationDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
}