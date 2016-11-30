package com.efe13.tdt.model.dto;

import java.util.HashSet;
import java.util.Set;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;

public class ChannelBandDTO extends DTOAPI {

	private short id;
	private String name;
	private String description;
	private boolean active;
	private Set<ChannelDTO> channels = new HashSet<>();
	
	public Short getId() {
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<ChannelDTO> getChannels() {
		return channels;
	}

	public void setChannels(Set<ChannelDTO> channels) {
		this.channels = channels;
	}
	
}