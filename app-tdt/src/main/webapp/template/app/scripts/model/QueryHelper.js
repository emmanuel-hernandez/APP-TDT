/**
 * 
 */

var QueryHelper = {
		build: function(pagination, filter) {
			return {
				paginationAPI: pagination,
				filterAPI: filter
			}
		}
}