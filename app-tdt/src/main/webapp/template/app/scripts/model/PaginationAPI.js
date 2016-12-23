/**
 * Object representation for a Pagination element
 */

var PaginationAPI = {
	build: function(p, size, t, tp) {
		return {
			page: p,
			pageSize: size,
			total: t,
			totalPage: tp
		}
	}
}