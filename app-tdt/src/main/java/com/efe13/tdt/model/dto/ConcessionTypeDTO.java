package com.efe13.tdt.model.dto;

import java.util.HashSet;
import java.util.Set;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;

public class ConcessionTypeDTO extends DTOAPI {

	private short id;
	private String type;
	private String description;
	private boolean active;
	private Set<ChannelDTO> channels = new HashSet<>();
	
	@Override
	public Short getId() {
		return id;
	}
	
	@Override
	public void setId(Number id) {
		this.id = (short) id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Boolean isActive() {
		return active;
	}

	@Override
	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<ChannelDTO> getChannels() {
		return channels;
	}

	public void setChannels(Set<ChannelDTO> channels) {
		this.channels = channels;
	}
	
}