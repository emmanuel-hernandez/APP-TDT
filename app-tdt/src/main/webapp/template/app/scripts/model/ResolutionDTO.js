/**
 * 
 */
var ResolutionDTO = (function() {
	function ResolutionDTO(id, name) {
		this.id = id;
		this.name = name;
	}
	
	ResolutionDTO.prototype.setId = function(id) {
		this.id = id
	};
	
	ResolutionDTO.prototype.getId = function() {
		return this.id
	};
	
	ResolutionDTO.prototype.setName = function(name) {
		this.name = name
	};
	
	ResolutionDTO.prototype.getName = function() {
		return this.name
	};
	
	return ResolutionDTO;
}());