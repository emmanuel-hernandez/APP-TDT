package com.efe13.tdt.service;

import com.efe13.mvc.commons.api.exception.ServiceException;
import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.efe13.mvc.service.api.impl.ServiceAPI;
import com.efe13.tdt.model.dto.StateDTO;

public class StateService extends ServiceAPI {

	@Override
	public StateDTO getById( DTOAPI object ) {
		throw new ServiceException( "This method has not implementation. It needs to be implemented by the concrete class" );
	}	
}