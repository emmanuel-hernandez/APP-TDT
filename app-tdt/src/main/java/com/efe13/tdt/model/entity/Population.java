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
	private int id;
	
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