'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # HomeController
 * Controller of the sbAdminApp
 */
angular.module( APP_NAME ).controller( 'HomeController', ['$scope', '$http', function( $scope, $http ) {
	
	var init = function() {
		$scope.alert = AlertDTO.build( null, ERROR_MESSAGE, false );
		$scope.statsNumber = STATS_NUMBER;
		$scope.stats = new Array();
	}
	
	var getStates = function() {
		$http.get( STATE_URL ).success( function(data) {
			if( data.statusResult.value ) {
				$scope.states = getSize( data.collection );
				$scope.stats.push( StatDTO.build( $scope.states, 'Estados', 'primary', 'bank', 'dashboard.state' ) );
			}
			else {
				$scope.alert.message = data.message;
			}
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}
		});
	};
	
	var getPopulations = function() {
		$http.get( POPULATION_URL ).success( function(data) {
			if( data.statusResult.value ) {
				$scope.populations = getSize( data.collection );
				$scope.stats.push( StatDTO.build( $scope.populations, 'Poblaciones', 'red', 'users', 'dashboard.population' ) );
			}
			else {
				$scope.alert.message = data.message;
			}
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}
		});
	};
	
	var getConcessionaires = function() {
		$http.get( CONCESSIONAIRE_URL ).success( function(data) {
			if( data.statusResult.value ) {
				$scope.concessionaires = getSize( data.collection );
				$scope.stats.push( StatDTO.build( $scope.concessionaires, 'Concesionarias', 'yellow', 'wifi', 'dashboard.concessionaire' ) );
			}
			else {
				$scope.alert.message = data.message;
			}
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}
		});
	};

	var getChannels = function() {
		$http.get( CHANNEL_URL ).success( function(data) {
			if( data.statusResult.value ) {
				$scope.channels = getSize( data.collection );
				$scope.stats.push( StatDTO.build( $scope.channels, 'Canales', 'green', 'video-camera', 'dashboard.channel' ) );
			}
			else {
				$scope.alert.message = data.message;
			}
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}
		});
	};
	
	var getSize = function( collection ) {
		var size = 0;
		for( var i=0; i<collection.length; i++ ) {
			if( collection[i].active ) {
				size += 1;
			}
		}
		
		return size;
	}

	init();
	getStates();
	getPopulations();
	getConcessionaires();
	getChannels();
}]);
