package com.efe13.tdt.model.dto;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;

public class StateDTO extends DTOAPI {
	
	private short id;
	private String name;
	private String shortName;

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
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