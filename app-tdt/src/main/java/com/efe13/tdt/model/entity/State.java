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
@Table( name="state" )
public class State extends EntityAPI {

	@Id
	@Column( name="stateId" )
	@GeneratedValue( strategy=GenerationType.AUTO )
	private short id;
	
	@Column( name="name" )
	private String name;
	
	@Column( name="shortName" )
	private String shortName;
	
	@Column( name="active" )
	private boolean active;
	
	@OneToMany( fetch=FetchType.EAGER, mappedBy="state" )
	private Set<Population> populations = new HashSet<>();
	
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
	
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Set<Population> getPopulations() {
		return populations;
	}

	public void setPopulations(Set<Population> populations) {
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