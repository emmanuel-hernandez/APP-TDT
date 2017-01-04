/**
 * Object representation for a Pagination element
 */

var PaginationAPI = {
	build: function(cPage, pSize, cSize, tPages) {
		return {
			currentPage: cPage,
			pageSize: pSize,
			collectionSize: cSize,
			totalPages: tPages
		}
	}
}