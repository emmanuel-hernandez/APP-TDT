package com.efe13.tdt.model.dto;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PopulationDTO extends DTOAPI {

	private short id;
	private String name;
	private boolean active;
	
	@JsonIgnoreProperties("populations")
	private StateDTO state;
	
	@Override
	public Short getId() {
		return id;
	}
	
	@Override
	public void setId(Number id) {
		this.id = (short) id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Boolean isActive() {
		return active;
	}

	@Override
	public void setActive(Boolean active) {
		this.active = active;
	}

	public StateDTO getState() {
		return state;
	}

	public void setState(StateDTO state) {
		this.state = state;
	}
	
}