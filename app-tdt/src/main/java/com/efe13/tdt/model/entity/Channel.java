package com.efe13.tdt.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.efe13.mvc.model.api.impl.entity.EntityAPI;

@Entity
@Table( name="channel" )
public class Channel extends EntityAPI {

	@Id
	@Column( name="channelId" )
	@GeneratedValue( strategy=GenerationType.AUTO )
	private short id;
	
	@Column( name="distinctive" )
	private String distinctive;
	
	@Column( name="virtualChannel" )
	private short virtualChannel;
	
	@Column( name="physicChannel" )
	private short physicChannel;
	
	@Column( name="power" )
	private short power;
	
	@Column( name="acesli" )
	private short acesli;
	
	@Column( name="latitude" )
	private String latitude;
	
	@Column( name="longitude" )
	private String longitude;
	
	@Column( name="effectiveDateStart" )
	private String effectiveDateStart;
	
	@Column( name="effectiveDateEnd" )
	private String effectiveDateEnd;
	
	@Column( name="channelBandId" )
	private short channelBandId;
	
	@Column( name="populationId" )
	private short populationId;
	
	@Column( name="concessionaireId" )
	private short concessionaireId;
	
	@Column( name="concessionTypeId" )
	private short concessionTypeId;
	
	@Column( name="active" )
	private boolean active;
	
	@Override
	public Short getId() {
		return id;
	}
	
	public void setId(short id) {
		this.id = id;
	}
	
	public String getDistinctive() {
		return distinctive;
	}

	public void setDistinctive(String distinctive) {
		this.distinctive = distinctive;
	}

	public short getVirtualChannel() {
		return virtualChannel;
	}

	public void setVirtualChannel(short virtualChannel) {
		this.virtualChannel = virtualChannel;
	}

	public short getPhysicChannel() {
		return physicChannel;
	}

	public void setPhysicChannel(short physicChannel) {
		this.physicChannel = physicChannel;
	}

	public short getPower() {
		return power;
	}

	public void setPower(short power) {
		this.power = power;
	}

	public short getAcesli() {
		return acesli;
	}

	public void setAcesli(short acesli) {
		this.acesli = acesli;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getEffectiveDateStart() {
		return effectiveDateStart;
	}

	public void setEffectiveDateStart(String efectiveDateStart) {
		this.effectiveDateStart = efectiveDateStart;
	}

	public String getEffectiveDateEnd() {
		return effectiveDateEnd;
	}

	public void setEffectiveDateEnd(String efectiveDateEnd) {
		this.effectiveDateEnd = efectiveDateEnd;
	}

	public short getChannelBandId() {
		return channelBandId;
	}

	public void setChannelBandId(short channelBandId) {
		this.channelBandId = channelBandId;
	}

	public short getPopulationId() {
		return populationId;
	}

	public void setPopulationId(short populationId) {
		this.populationId = populationId;
	}

	public short getConcessionaireId() {
		return concessionaireId;
	}

	public void setConcessionaireId(short concessionaireId) {
		this.concessionaireId = concessionaireId;
	}

	public short getConcessionTypeId() {
		return concessionTypeId;
	}

	public void setConcessionTypeId(short concessionTypeId) {
		this.concessionTypeId = concessionTypeId;
	}

	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}