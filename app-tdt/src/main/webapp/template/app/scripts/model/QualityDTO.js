/**
 * 
 */
var QualityDTO = (function() {
	function QualityDTO(id, name) {
		this.id = id;
		this.name = name;
	}
	
	QualityDTO.prototype.setId = function(id) {
		this.id = id
	};
	
	QualityDTO.prototype.getId = function() {
		return this.id
	};
	
	QualityDTO.prototype.setName = function(name) {
		this.name = name
	};
	
	QualityDTO.prototype.getName = function() {
		return this.name
	};
	
	return QualityDTO;
}());