package com.efe13.tdt.model.dto;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PopulationDTO extends DTOAPI {

	private int id;
	private String name;
	private boolean active;
	
	@JsonIgnoreProperties("populations")
	private StateDTO state;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public StateDTO getState() {
		return state;
	}

	public void setState(StateDTO state) {
		this.state = state;
	}
	
}