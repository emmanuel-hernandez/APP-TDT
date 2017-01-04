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
import com.efe13.tdt.model.dto.PopulationDTO;
import com.efe13.tdt.model.dto.StateDTO;
import com.efe13.tdt.service.impl.PopulationServiceImpl;
import com.efe13.tdt.service.impl.StateServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Emmanuel
 *
 */
@RestController
@CrossOrigin
@RequestMapping( "/population" )
public class PopulationController {
	
	private final static PopulationServiceImpl POPULATION_SERVICE = new PopulationServiceImpl();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public ServiceResult<PopulationDTO> getPopulations( @RequestParam(name="queryHelper", required=false) String serviceRequestString ) {
		try {
			QueryHelper queryHelper = null;
			
			if( serviceRequestString != null ) {
				queryHelper = new ObjectMapper().readValue( serviceRequestString, QueryHelper.class );
			}
			
			return POPULATION_SERVICE.listAll( queryHelper );
		}
		catch( Exception ex ) {
			return new ServiceResult<PopulationDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/state/{stateId}", method=RequestMethod.GET )
	public ServiceResult<PopulationDTO> getPopulationsByState( @PathVariable("stateId") short stateId ) {
		try {
			StateDTO stateDTO = new StateDTO();
			stateDTO.setId( stateId );
			return POPULATION_SERVICE.getByState( stateDTO );
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
			return POPULATION_SERVICE.getById( populationDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<PopulationDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}

	@RequestMapping( value="/", method=RequestMethod.POST )
	public ServiceResult<PopulationDTO> savePopulation( @RequestBody PopulationDTO populationDTO ) {
		try {
			ServiceResult<StateDTO> serviceResult = new StateServiceImpl().getById( populationDTO.getState() );
			if( serviceResult.getStatusResult() == StatusResultService.STATUS_SUCCESS ) {
				populationDTO.setActive( ActiveEnum.ACTIVE.getValue() );
				return POPULATION_SERVICE.savePopulation( populationDTO );
			}
			
			return new ServiceResult<>( serviceResult.getMessage(), serviceResult.getStatusResult() );
		}
		catch( Exception ex ) {
			return new ServiceResult<PopulationDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.PUT )
	public ServiceResult<PopulationDTO> updatePopulation( @PathVariable("id") short populationId, @RequestBody PopulationDTO populationDTO ) {
		try {
			ServiceResult<PopulationDTO> serviceResult = getPopulation( populationId );
			if( serviceResult.getStatusResult() == StatusResultService.STATUS_SUCCESS ) {
				populationDTO.setId( populationId );
				return POPULATION_SERVICE.update( populationDTO );
			}
			
			return serviceResult;
		}
		catch( Exception ex ) {
			return new ServiceResult<PopulationDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.DELETE )
	public ServiceResult<PopulationDTO> deletePopulation( @PathVariable("id") short populationId ) {
		try {
			ServiceResult<PopulationDTO> serviceResult = getPopulation( populationId );
			if( serviceResult.getStatusResult() == StatusResultService.STATUS_SUCCESS ) {
				return POPULATION_SERVICE.delete( serviceResult.getObject() );
			}
			
			return serviceResult;
		}
		catch( Exception ex ) {
			return new ServiceResult<PopulationDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
}