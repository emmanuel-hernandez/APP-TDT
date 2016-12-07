package com.efe13.tdt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.efe13.tdt.enums.StatusResultService;
import com.efe13.tdt.model.dto.ConcessionaireDTO;
import com.efe13.tdt.service.impl.ConcessionaireServiceImpl;
import com.efe13.tdt.util.ServiceResult;

/**
 * @author Emmanuel
 *
 */
@RestController
@CrossOrigin
@RequestMapping( "/concessionaire" )
public class ConcessionaireController {
	
	private final static ConcessionaireServiceImpl CONCESSIONAIRE_SERVICE = new ConcessionaireServiceImpl();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public ServiceResult<ConcessionaireDTO> getConcessionaires() {
		try {
			return CONCESSIONAIRE_SERVICE.listAll();
		}
		catch( Exception ex ) {
			return new ServiceResult<ConcessionaireDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public ServiceResult<ConcessionaireDTO> getConcessionaire( @PathVariable("id") short concessionaireId ) {
		try {
			ConcessionaireDTO concessionaireDTO = new ConcessionaireDTO();
			concessionaireDTO.setId( concessionaireId );
			return CONCESSIONAIRE_SERVICE.getById( concessionaireDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ConcessionaireDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/", method=RequestMethod.POST )
	public ServiceResult<ConcessionaireDTO> saveConcessionaire( @RequestBody ConcessionaireDTO concessionaireDTO ) {
		try {
			return CONCESSIONAIRE_SERVICE.saveConcessionaire( concessionaireDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ConcessionaireDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.PUT )
	public ServiceResult<ConcessionaireDTO> updateConcessionaire( @PathVariable("id") short concessionaireId, @RequestBody ConcessionaireDTO concessionaireDTO ) {
		try {
			ServiceResult<ConcessionaireDTO> serviceResult = getConcessionaire( concessionaireId );
			if( serviceResult.getStatusResult() == StatusResultService.STATUS_SUCCESS ) {
				concessionaireDTO.setId( concessionaireId );
				return CONCESSIONAIRE_SERVICE.update( concessionaireDTO );
			}
			
			return serviceResult;
		}
		catch( Exception ex ) {
			return new ServiceResult<ConcessionaireDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.DELETE )
	public ServiceResult<ConcessionaireDTO> deleteConcessionaire( @PathVariable("id") short concessionaireId ) {
		try {
			ServiceResult<ConcessionaireDTO> serviceResult = getConcessionaire( concessionaireId );
			if( serviceResult.getStatusResult() == StatusResultService.STATUS_SUCCESS ) {
				return CONCESSIONAIRE_SERVICE.delete( serviceResult.getObject() );
			}
			
			return serviceResult;
		}
		catch( Exception ex ) {
			return new ServiceResult<ConcessionaireDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
}