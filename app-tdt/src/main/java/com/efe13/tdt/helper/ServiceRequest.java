package com.efe13.tdt.helper;

import com.efe13.mvc.model.api.impl.helper.FilterAPI;
import com.efe13.mvc.model.api.impl.helper.PaginationAPI;

public class ServiceRequest {
	
	private PaginationAPI paginationDTO;
	private FilterAPI filterDTO;
	
	public PaginationAPI getPaginationDTO() {
		return paginationDTO;
	}
	
	public void setPaginationDTO(PaginationAPI paginationDTO) {
		this.paginationDTO = paginationDTO;
	}
	
	public FilterAPI getFilterDTO() {
		return filterDTO;
	}
	
	public void setFilterDTO(FilterAPI filterDTO) {
		this.filterDTO = filterDTO;
	}
}