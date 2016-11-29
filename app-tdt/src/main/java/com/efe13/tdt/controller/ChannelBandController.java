package com.efe13.tdt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.model.dto.ChannelBandDTO;
import com.efe13.tdt.service.ChannelBandService;

/**
 * @author Emmanuel
 *
 */
@RestController
@RequestMapping( "/channelBand" )
public class ChannelBandController {
	
	private final static ServiceAPI service = new ChannelBandService();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public List<DTOAPI> getChannelBands() {
		return service.getAll();
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public DTOAPI getPopultation( @PathVariable("id") short populationId ) {
		ChannelBandDTO population = new ChannelBandDTO();
		population.setId( populationId );
		
		return service.getById( population );
	}
}