/**
 * 
 */
angular.module( APP_NAME ).factory( 'httpService', ['$http', function( $http ) {
	return {
		get: function( url ) {
			return $http.get( url );
		},
		post: function( url ) {
			return $http.post( url );
		},
		put: function( url ) {
			return $http.put( url );
		},
		delete: function( url ) {
			return $http.delete( url );
		}
	}
}]);