/**
 * 
 */
var KeyValueDTO = (function() {
	function KeyValueDTO(key, value) {
		this.key = key;
		this.value = value;
	}
	
	KeyValueDTO.prototype.setKey = function(key) {
		this.key = key
	};
	
	KeyValueDTO.prototype.getKey = function() {
		return this.key
	};
	
	KeyValueDTO.prototype.setValue = function(value) {
		this.value = value
	};
	
	KeyValueDTO.prototype.getValue = function() {
		return this.value
	};
	
	return KeyValueDTO;
}());