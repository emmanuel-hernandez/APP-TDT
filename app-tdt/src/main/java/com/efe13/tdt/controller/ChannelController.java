package com.efe13.tdt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.model.dto.ChannelDTO;
import com.efe13.tdt.service.ChannelService;

/**
 * @author Emmanuel
 *
 */
@RestController
@RequestMapping( "/channel" )
public class ChannelController {
	
	private final static ServiceAPI service = new ChannelService();
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public List<DTOAPI> getChannels() {
		return service.getAll();
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public DTOAPI getPopultation( @PathVariable("id") short populationId ) {
		ChannelDTO population = new ChannelDTO();
		population.setId( populationId );
		
		return service.getById( population );
	}
}