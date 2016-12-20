/**
 * 
 */

var QueryHelper = {
	build: function(pagination, filter, order) {
		return {
			paginationAPI: pagination,
			filterAPI: filter,
			orderAPI: order
		}
	}
}