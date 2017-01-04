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
import com.efe13.tdt.model.dto.ChannelBandDTO;
import com.efe13.tdt.model.dto.ChannelDTO;
import com.efe13.tdt.model.dto.ConcessionTypeDTO;
import com.efe13.tdt.model.dto.ConcessionaireDTO;
import com.efe13.tdt.model.dto.PopulationDTO;
import com.efe13.tdt.service.impl.ChannelBandServiceImpl;
import com.efe13.tdt.service.impl.ChannelServiceImpl;
import com.efe13.tdt.service.impl.ConcessionTypeServiceImpl;
import com.efe13.tdt.service.impl.ConcessionaireServiceImpl;
import com.efe13.tdt.service.impl.PopulationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Emmanuel
 *
 */
@RestController
@CrossOrigin
@RequestMapping( "/channel" )
public class ChannelController {
	
	private final static ChannelServiceImpl CHANNEL_SERVICE = new ChannelServiceImpl();
	
	@RequestMapping( value="/", method=RequestMethod.GET)
	public ServiceResult<ChannelDTO> getChannels( @RequestParam(name="queryHelper", required=false) String serviceRequestString ) {
		try {
			QueryHelper queryHelper = null;
			
			if( serviceRequestString != null ) {
				queryHelper = new ObjectMapper().readValue( serviceRequestString, QueryHelper.class );
			}
			
			return CHANNEL_SERVICE.listAll( queryHelper );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/population/{id}", method=RequestMethod.GET )
	public ServiceResult<ChannelDTO> getChannelsByPopulation( @PathVariable("id") short populationId ) {
		try {
			PopulationDTO populationDTO  = new PopulationDTO();
			populationDTO.setId( populationId );
			return CHANNEL_SERVICE.getByPopulation( populationDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/concessionaire/{id}", method=RequestMethod.GET )
	public ServiceResult<ChannelDTO> getChannelsByConcessionaire( @PathVariable("id") short concessionaireId ) {
		try {
			ConcessionaireDTO concessionaireDTO  = new ConcessionaireDTO();
			concessionaireDTO.setId( concessionaireId );
			return CHANNEL_SERVICE.getByConcessionaire( concessionaireDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/concessionType/{id}", method=RequestMethod.GET )
	public ServiceResult<ChannelDTO> getChannelsByConcessionType( @PathVariable("id") short concessionTypeId ) {
		try {
			ConcessionTypeDTO concessionTypeDTO  = new ConcessionTypeDTO();
			concessionTypeDTO.setId( concessionTypeId );
			return CHANNEL_SERVICE.getByConcessionType( concessionTypeDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public ServiceResult<ChannelDTO> getChannel( @PathVariable("id") short channelId ) {
		try {
			ChannelDTO channelDTO = new ChannelDTO();
			channelDTO.setId( channelId );
			return CHANNEL_SERVICE.getById( channelDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/", method=RequestMethod.POST )
	public ServiceResult<ChannelDTO> saveChannel( @RequestBody ChannelDTO channelDTO ) {
		try {
			ServiceResult<ChannelDTO> channelResult = new ServiceResult<>();
			
			ServiceResult<ChannelBandDTO> channelBandResult = new ChannelBandServiceImpl().getById( channelDTO.getChannelBand() );
			if( channelBandResult.getStatusResult() == StatusResultService.STATUS_FAILED ) {
				channelResult.setMessage( channelBandResult.getMessage() );
				channelResult.setStatusResult( channelBandResult.getStatusResult() );
				
				return channelResult;
			}
			
			ServiceResult<PopulationDTO> populationResult = new PopulationServiceImpl().getById( channelDTO.getPopulation() );
			if( populationResult.getStatusResult() == StatusResultService.STATUS_FAILED ) {
				channelResult.setMessage( populationResult.getMessage() );
				channelResult.setStatusResult( populationResult.getStatusResult() );
				
				return channelResult;
			}
			
			ServiceResult<ConcessionaireDTO> concessionaireResult = new ConcessionaireServiceImpl().getById( channelDTO.getConcessionaire() );
			if( concessionaireResult.getStatusResult() == StatusResultService.STATUS_FAILED ) {
				channelResult.setMessage( concessionaireResult.getMessage() );
				channelResult.setStatusResult( concessionaireResult.getStatusResult() );
				
				return channelResult;
			}
			
			ServiceResult<ConcessionTypeDTO> concessionTypeResult = new ConcessionTypeServiceImpl().getById( channelDTO.getConcessionType() );
			if( concessionTypeResult.getStatusResult() == StatusResultService.STATUS_FAILED ) {
				channelResult.setMessage( concessionTypeResult.getMessage() );
				channelResult.setStatusResult( concessionTypeResult.getStatusResult() );
				
				return channelResult;
			}
			
			channelDTO.setActive( ActiveEnum.ACTIVE.getValue() );
			return CHANNEL_SERVICE.saveChannel( channelDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}

	@RequestMapping( value="/{id}", method=RequestMethod.PUT )
	public ServiceResult<ChannelDTO> updateChannel( @PathVariable("id") short channelId, @RequestBody ChannelDTO channelDTO ) {
		try {
			ServiceResult<ChannelDTO> serviceResult = getChannel( channelId );
			if( serviceResult.getStatusResult() == StatusResultService.STATUS_SUCCESS ) {
				serviceResult.getObject().setId( channelId );
				return CHANNEL_SERVICE.update( channelDTO );
			}
			
			return serviceResult;
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.DELETE )
	public ServiceResult<ChannelDTO> deleteChannel( @PathVariable("id") short channelId ) {
		try {
			ServiceResult<ChannelDTO> serviceResult = getChannel( channelId );
			if( serviceResult.getStatusResult() == StatusResultService.STATUS_SUCCESS ) {
				return CHANNEL_SERVICE.delete( serviceResult.getObject() );
			}
			
			return serviceResult;
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
}