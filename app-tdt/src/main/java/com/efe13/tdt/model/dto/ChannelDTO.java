package com.efe13.tdt.model.dto;

import com.efe13.mvc.model.api.impl.dto.DTOAPI;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ChannelDTO extends DTOAPI {
	
	private short id;
	private String distinctive;
	private String name;
	private float virtualChannel;
	private short physicChannel;
	private String quality;
	private String resolution;
	private short power;
	private short acesli;
	private String latitude;
	private String longitude;
	private String effectiveDateStart;
	private String effectiveDateEnd;
	
	@JsonIgnoreProperties("channels")
	private ChannelBandDTO channelBand;
	
	@JsonIgnoreProperties("channels")
	private PopulationDTO population;
	
	@JsonIgnoreProperties("channels")
	private ConcessionaireDTO concessionaire;
	
	@JsonIgnoreProperties("channels")
	private ConcessionTypeDTO concessionType;
	private boolean active;

	@Override
	public Short getId() {
		return id;
	}
	
	@Override
	public void setId(Number id) {
		this.id = new Short( String.valueOf(id) );
	}
	
	public String getDistinctive() {
		return distinctive;
	}

	public void setDistinctive(String distinctive) {
		this.distinctive = distinctive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getVirtualChannel() {
		return virtualChannel;
	}

	public void setVirtualChannel(float virtualChannel) {
		this.virtualChannel = virtualChannel;
	}

	public short getPhysicChannel() {
		return physicChannel;
	}

	public void setPhysicChannel(short physicChannel) {
		this.physicChannel = physicChannel;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
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

	public ChannelBandDTO getChannelBand() {
		return channelBand;
	}

	public void setChannelBand(ChannelBandDTO channelBand) {
		this.channelBand = channelBand;
	}

	public PopulationDTO getPopulation() {
		return population;
	}

	public void setPopulation(PopulationDTO population) {
		this.population = population;
	}

	public ConcessionaireDTO getConcessionaire() {
		return concessionaire;
	}

	public void setConcessionaire(ConcessionaireDTO concessionaire) {
		this.concessionaire = concessionaire;
	}

	public ConcessionTypeDTO getConcessionType() {
		return concessionType;
	}

	public void setConcessionType(ConcessionTypeDTO concessionType) {
		this.concessionType = concessionType;
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