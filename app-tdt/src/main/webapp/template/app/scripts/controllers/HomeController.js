'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # HomeController
 * Controller of the sbAdminApp
 */
angular.module( APP_NAME ).controller( 'HomeController', ['$scope', function( $scope ) {
	
	var init = function() {
		$scope.statsNumber = STATS_NUMBER;
		$scope.stats = new Array();
		
		$scope.stats.push( StatDTO.build( 32, 'Estados', 'primary', 'bank', 'dashboard.state' ) );
		$scope.stats.push( StatDTO.build( 326, 'Poblaciones', 'red', 'users', 'dashboard.population' ) );
		$scope.stats.push( StatDTO.build( 32, 'Concesionarias', 'yellow', 'wifi', 'dashboard.concessionaire' ) );
		$scope.stats.push( StatDTO.build( 32, 'Canales', 'green', 'video-camera', 'dashboard.channel' ) );
	}
	
	init();
}]);
