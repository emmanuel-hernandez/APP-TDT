package com.efe13.tdt.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efe13.tdt.enums.StatusResultService;
import com.efe13.tdt.model.dto.ConcessionTypeDTO;
import com.efe13.tdt.service.impl.ConcessionTypeServiceImpl;
import com.efe13.tdt.util.ServiceResult;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Emmanuel
 *
 */
@RestController
@RequestMapping( "/concessionType" )
public class ConcessionTypeController {
	
	private final static ConcessionTypeServiceImpl CONCESSION_TYPE_SERVICE = new ConcessionTypeServiceImpl();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public ServiceResult<ConcessionTypeDTO> getConcessionTypes() {
		try {
			return CONCESSION_TYPE_SERVICE.listAll();
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
	public ServiceResult<ConcessionTypeDTO> saveConcessionType( @RequestParam("concessionType") String jsonConcessionType ) {
		try {
			ConcessionTypeDTO concessionTypeDTO = new ObjectMapper().readValue( jsonConcessionType, ConcessionTypeDTO.class );
			return CONCESSION_TYPE_SERVICE.saveConcessionType( concessionTypeDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ConcessionTypeDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/", method=RequestMethod.PUT )
	public ServiceResult<ConcessionTypeDTO> updateConcessionType( @RequestParam("concessionType") String jsonConcessionType ) {
		try {
			ConcessionTypeDTO concessionTypeDTO = new ObjectMapper().readValue( jsonConcessionType, ConcessionTypeDTO.class );
			return CONCESSION_TYPE_SERVICE.update( concessionTypeDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ConcessionTypeDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
	
	@RequestMapping( value="/", method=RequestMethod.DELETE )
	public ServiceResult<ConcessionTypeDTO> deleteConcessionType( @RequestParam("concessionType") String jsonConcessionType ) {
		try {
			ConcessionTypeDTO concessionTypeDTO = new ObjectMapper().readValue( jsonConcessionType, ConcessionTypeDTO.class );
			return CONCESSION_TYPE_SERVICE.delete( concessionTypeDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ConcessionTypeDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
}