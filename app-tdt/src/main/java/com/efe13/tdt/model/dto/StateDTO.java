package com.efe13.tdt.model.dto;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;

public class StateDTO extends DTOAPI {
	
	private short stateId;
	private String name;
	private String shortName;

	public short getStateId() {
		return stateId;
	}

	public void setStateId(short stateId) {
		this.stateId = stateId;
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

}