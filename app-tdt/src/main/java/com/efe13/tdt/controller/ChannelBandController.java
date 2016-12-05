package com.efe13.tdt.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efe13.tdt.enums.StatusResultService;
import com.efe13.tdt.model.dto.ChannelBandDTO;
import com.efe13.tdt.service.impl.ChannelBandServiceImpl;
import com.efe13.tdt.util.ServiceResult;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Emmanuel
 *
 */
@RestController
@RequestMapping( "/channelBand" )
public class ChannelBandController {

	private final static ChannelBandServiceImpl CHANNEL_BAND_SERVICE = new ChannelBandServiceImpl();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public ServiceResult<ChannelBandDTO> getChannelBands() {
		try {
			return CHANNEL_BAND_SERVICE.listAll();
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
	public ServiceResult<ChannelBandDTO> saveChannelBand( @RequestParam("channelBand") String jsonChannelBand ) {
		try {
			ChannelBandDTO channelBandDTO = new ObjectMapper().readValue( jsonChannelBand, ChannelBandDTO.class );
			return CHANNEL_BAND_SERVICE.saveChannelBand( channelBandDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelBandDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/", method=RequestMethod.PUT )
	public ServiceResult<ChannelBandDTO> updateChannelBand( @RequestParam("channelBand") String jsonChannelBand ) {
		try {
			ChannelBandDTO channelBandDTO = new ObjectMapper().readValue( jsonChannelBand, ChannelBandDTO.class );
			return CHANNEL_BAND_SERVICE.update( channelBandDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelBandDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
	
	@RequestMapping( value="/", method=RequestMethod.DELETE )
	public ServiceResult<ChannelBandDTO> deleteChannelBand( @RequestParam("channelBand") String jsonChannelBand ) {
		try {
			ChannelBandDTO channelBandDTO = new ObjectMapper().readValue( jsonChannelBand, ChannelBandDTO.class );
			return CHANNEL_BAND_SERVICE.delete( channelBandDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelBandDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
}