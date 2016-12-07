package com.efe13.tdt.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.efe13.mvc.model.api.impl.entity.EntityAPI;

@Entity
@Table( name="channelBand" )
public class ChannelBand extends EntityAPI {

	@Id
	@Column( name="channelBandId" )
	@GeneratedValue( strategy=GenerationType.AUTO )
	private short id;
	
	@Column( name="name" )
	private String name;
	
	@Column( name="description" )
	private String description;
	
	@Column( name="active" )
	private boolean active;
	
	@OneToMany( fetch=FetchType.EAGER, mappedBy="channelBand" )
	private Set<Channel> channels = new HashSet<>();
	
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

	public Set<Channel> getChannels() {
		return channels;
	}

	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}
}