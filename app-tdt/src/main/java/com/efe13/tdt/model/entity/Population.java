package com.efe13.tdt.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.efe13.mvc.model.api.impl.entity.EntityAPI;

@Entity
@Table( name="population" )
public class Population extends EntityAPI {

	@Id
	@Column( name="populationId" )
	@GeneratedValue( strategy=GenerationType.AUTO )
	private short id;
	
	@Column( name="name" )
	private String name;
	
	@Column( name="active" )
	private boolean active;
	
	@ManyToOne( fetch=FetchType.LAZY, cascade=CascadeType.ALL )
	@JoinColumn( name="stateId" )
	private State state;
	
	@OneToMany( cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="population" )
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
	
	@Override
	public Boolean isActive() {
		return active;
	}
	
	@Override
	public void setActive(Boolean active) {
		this.active = active;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Set<Channel> getChannels() {
		return channels;
	}

	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}
}