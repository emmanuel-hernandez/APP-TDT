package com.efe13.tdt.model.dto;

import java.util.HashSet;
import java.util.Set;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;

public class StateDTO extends DTOAPI {
	
	private short id;
	private String name;
	private String shortName;
	private boolean active;
	private Set<PopulationDTO> populations = new HashSet<>();

	@Override
	public Short getId() {
		return id;
	}

	@Override
	public void setId(Number id) {
		this.id = new Short( String.valueOf(id) );
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Set<PopulationDTO> getPopulations() {
		return populations;
	}

	public void setPopulations(Set<PopulationDTO> populations) {
		this.populations = populations;
	}

	@Override
	public Boolean isActive() {
		return active;
	}

	@Override
	public void setActive(Boolean active) {
		this.active = active;
	}
}