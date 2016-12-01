package com.efe13.tdt.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne( fetch=FetchType.LAZY, cascade=CascadeType.ALL )
	@JoinColumn( name="channelBandId" )
	private ChannelBand channelBand;
	
	@ManyToOne( fetch=FetchType.LAZY, cascade=CascadeType.ALL )
	@JoinColumn( name="populationId" )
	private Population population;
	
	@ManyToOne( fetch=FetchType.LAZY, cascade=CascadeType.ALL )
	@JoinColumn( name="concessionaireId" )
	private Concessionaire concessionaire;
	
	@ManyToOne( fetch=FetchType.LAZY, cascade=CascadeType.ALL )
	@JoinColumn( name="concessionTypeId" )
	private ConcessionType concessionType;
	
	@Column( name="active" )
	private boolean active;
	
	@Override
	public Short getId() {
		return id;
	}
	
	@Override
	public void setId(Number id) {
		this.id = (short)id;
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

	public ChannelBand getChannelBand() {
		return channelBand;
	}

	public void setChannelBand(ChannelBand channelBandId) {
		this.channelBand = channelBandId;
	}

	public Population getPopulation() {
		return population;
	}

	public void setPopulation(Population populationId) {
		this.population = populationId;
	}

	public Concessionaire getConcessionaire() {
		return concessionaire;
	}

	public void setConcessionaire(Concessionaire concessionaireId) {
		this.concessionaire = concessionaireId;
	}

	public ConcessionType getConcessionType() {
		return concessionType;
	}

	public void setConcessionType(ConcessionType concessionTypeId) {
		this.concessionType = concessionTypeId;
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