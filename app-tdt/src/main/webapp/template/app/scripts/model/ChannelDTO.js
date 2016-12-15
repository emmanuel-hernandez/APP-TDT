/**
 * Object representation for a Channel element
 */

var ChannelDTO = (function() {
	function ChannelDTO() {
	}

	ChannelDTO.prototype.setId = function( id ) {
	        this.id = id;
	}

	ChannelDTO.prototype.getId = function() {
	        return this.id;
	}

	ChannelDTO.prototype.setDistinctive = function( distinctive ) {
	        this.distinctive = distinctive;
	}

	ChannelDTO.prototype.getDistinctive = function() {
	        return this.distinctive;
	}

	ChannelDTO.prototype.setName = function( name ) {
	        this.name = name;
	}

	ChannelDTO.prototype.getName = function() {
	        return this.name;
	}

	ChannelDTO.prototype.setVirtualChannel = function( virtualChannel ) {
	        this.virtualChannel = virtualChannel;
	}

	ChannelDTO.prototype.getVirtualChannel = function() {
	        return this.virtualChannel;
	}

	ChannelDTO.prototype.setPhysicChannel = function( physicChannel ) {
	        this.physicChannel = physicChannel;
	}

	ChannelDTO.prototype.getPhysicChannel = function() {
	        return this.physicChannel;
	}

	ChannelDTO.prototype.setQuality = function( quality ) {
	        this.quality = quality;
	}

	ChannelDTO.prototype.getQuality = function() {
	        return this.quality;
	}

	ChannelDTO.prototype.setResolution = function( resolution ) {
	        this.resolution = resolution;
	}

	ChannelDTO.prototype.getResolution = function() {
	        return this.resolution;
	}

	ChannelDTO.prototype.setPower = function( power ) {
	        this.power = power;
	}

	ChannelDTO.prototype.getPower = function() {
	        return this.power;
	}

	ChannelDTO.prototype.setAcesli = function( acesli ) {
	        this.acesli = acesli;
	}

	ChannelDTO.prototype.getAcesli = function() {
	        return this.acesli;
	}

	ChannelDTO.prototype.setLatitude = function( latitude ) {
	        this.latitude = latitude;
	}

	ChannelDTO.prototype.getLatitude = function() {
	        return this.latitude;
	}

	ChannelDTO.prototype.setLongitude = function( longitude ) {
	        this.longitude = longitude;
	}

	ChannelDTO.prototype.getLongitude = function() {
	        return this.longitude;
	}

	ChannelDTO.prototype.setEffectiveDateStart = function( effectiveDateStart ) {
	        this.effectiveDateStart = effectiveDateStart;
	}

	ChannelDTO.prototype.getEffectiveDateStart = function() {
	        return this.effectiveDateStart;
	}

	ChannelDTO.prototype.setEffectiveDateEnd = function( effectiveDateEnd ) {
	        this.effectiveDateEnd = effectiveDateEnd;
	}

	ChannelDTO.prototype.getEffectiveDateEnd = function() {
	        return this.effectiveDateEnd;
	}

	ChannelDTO.prototype.setChannelBand = function( channelBand ) {
	        this.channelBand = channelBand;
	}

	ChannelDTO.prototype.getChannelBand = function() {
	        return this.channelBand;
	}

	ChannelDTO.prototype.setPopulation = function( population ) {
	        this.population = population;
	}

	ChannelDTO.prototype.getPopulation = function() {
	        return this.population;
	}

	ChannelDTO.prototype.setConcessionaire = function( concessionaire ) {
	        this.concessionaire = concessionaire;
	}

	ChannelDTO.prototype.getConcessionaire = function() {
	        return this.concessionaire;
	}

	ChannelDTO.prototype.setConcessionType = function( concessionType ) {
	        this.concessionType = concessionType;
	}

	ChannelDTO.prototype.getConcessionType = function() {
	        return this.concessionType;
	}
	
	return ChannelDTO;
}());