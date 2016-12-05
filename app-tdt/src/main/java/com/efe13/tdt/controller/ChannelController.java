package com.efe13.tdt.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efe13.tdt.enums.StatusResultService;
import com.efe13.tdt.model.dto.ChannelDTO;
import com.efe13.tdt.service.impl.ChannelServiceImpl;
import com.efe13.tdt.util.ServiceResult;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Emmanuel
 *
 */
@RestController
@RequestMapping( "/channel" )
public class ChannelController {
	
	private final static ChannelServiceImpl CHANNEL_SERVICE = new ChannelServiceImpl();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public ServiceResult<ChannelDTO> getChannels() {
		try {
			return CHANNEL_SERVICE.listAll();
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
	public ServiceResult<ChannelDTO> saveChannel( @RequestParam("channel") String jsonChannel ) {
		try {
			ChannelDTO channelDTO = new ObjectMapper().readValue( jsonChannel, ChannelDTO.class );
			return CHANNEL_SERVICE.saveChannel( channelDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}
	}
	
	@RequestMapping( value="/", method=RequestMethod.PUT )
	public ServiceResult<ChannelDTO> updateChannel( @RequestParam("channel") String jsonChannel ) {
		try {
			ChannelDTO channelDTO = new ObjectMapper().readValue( jsonChannel, ChannelDTO.class );
			return CHANNEL_SERVICE.update( channelDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
	
	@RequestMapping( value="/", method=RequestMethod.DELETE )
	public ServiceResult<ChannelDTO> deleteChannel( @RequestParam("channel") String jsonChannel ) {
		try {
			ChannelDTO channelDTO = new ObjectMapper().readValue( jsonChannel, ChannelDTO.class );
			return CHANNEL_SERVICE.delete( channelDTO );
		}
		catch( Exception ex ) {
			return new ServiceResult<ChannelDTO>( ex.getMessage(), StatusResultService.STATUS_FAILED );
		}

	}
}