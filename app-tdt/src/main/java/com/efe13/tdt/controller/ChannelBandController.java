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
import com.efe13.tdt.service.impl.ChannelBandServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Emmanuel
 *
 */
@RestController
@CrossOrigin
@RequestMapping( "/channelBand" )
public class ChannelBandController {

	private final static ChannelBandServiceImpl CHANNEL_BAND_SERVICE = new ChannelBandServiceImpl();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public ServiceResult<ChannelBandDTO> getChannelBands( @RequestParam(name="queryHelper", required=false) String serviceRequestString ) {
		try {
			QueryHelper queryHelper = null;
			
			if( serviceRequestString != null ) {
				queryHelper = new ObjectMapper().readValue( serviceRequestString, QueryHelper.class );
			}
			
			return CHANNEL_BAND_SERVICE.listAll( queryHelper );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelBandDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public ServiceResult<ChannelBandDTO> getChannelBand( @PathVariable("id") short channelBandId ) {
		try {
			ChannelBandDTO channelBandDTO = new ChannelBandDTO();
			channelBandDTO.setId( channelBandId );
			return CHANNEL_BAND_SERVICE.getById( channelBandDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelBandDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/", method=RequestMethod.POST )
	public ServiceResult<ChannelBandDTO> saveChannelBand( @RequestBody ChannelBandDTO channelBandDTO ) {
		try {
			channelBandDTO.setActive( ActiveEnum.ACTIVE.getValue() );
			return CHANNEL_BAND_SERVICE.saveChannelBand( channelBandDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelBandDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.PUT )
	public ServiceResult<ChannelBandDTO> updateChannelBand( @PathVariable("id") short channelBandId, @RequestBody ChannelBandDTO channelBandDTO ) {
		try {
			ServiceResult<ChannelBandDTO> serviceResult = getChannelBand( channelBandId );
			if( serviceResult.getStatusResult() == StatusResultService.STATUS_SUCCESS ) {
				serviceResult.getObject().setId( channelBandId );
				return CHANNEL_BAND_SERVICE.update( channelBandDTO );
			}
			
			return serviceResult;
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelBandDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.DELETE )
	public ServiceResult<ChannelBandDTO> deleteChannelBand( @PathVariable("id") short channelBandId ) {
		try {
			ServiceResult<ChannelBandDTO> serviceResult = getChannelBand( channelBandId );
			if( serviceResult.getStatusResult() == StatusResultService.STATUS_SUCCESS ) {
				return CHANNEL_BAND_SERVICE.delete( serviceResult.getObject() );
			}
			
			return serviceResult;
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelBandDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
}