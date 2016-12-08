'use strict';
/**
 * @ngdoc function
 * @name app-tdt.controller:PopulationController
 * @description
 * # PopulationController
 * Controller of the population.html view
 */
angular.module( APP_NAME ).controller( 'PopulationController',
	function($scope, $position, $http) {
		const URL_POPULATION = 'http://localhost:8181/population/';
		const URL_STATE = 'http://localhost:8181/state/';
		$scope.population = {
			id: 0,
			name: null,
			state: {
				id: 0,
				name: null,
				shortName: null
			}
		};
		var defaultState = {
			id: -1,
			name: 'Seleccionar...'
		};
		
		$scope.selectedState = defaultState;
		var isUpdate = false;
		$scope.populations = new Array();
		$scope.states = new Array();
		$scope.alerts = {
			message: '',
			type: '',
			show: false
		};

		var getStates = function() {
			$http.get( URL_STATE ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.states = data.collection;
					$scope.states.unshift( defaultState )
				}
				else {
					$scope.alerts.show = true;
					$scope.alerts.resultMessage = data.message;
				}
			});
		};
		
		$scope.get = function() {
			$http.get( URL_POPULATION ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.populations = data.collection;
				}
				else {
					$scope.alerts.show = true;
					$scope.alerts.resultMessage = data.message;
				}
			});
		}
		
		$scope.save = function() {
			$scope.alerts.show = true;
			for( var i=0; i<$scope.states.length; i++ ) {
				if( $scope.states[i].id == $scope.selectedState.id ) {
					$scope.population.state = $scope.states[i];
					break;
				}
			}
			
			if( !isUpdate ) {
				$http.post( URL_POPULATION, JSON.stringify($scope.population) ).success( function(data) {
					$scope.alerts.type = ( data.statusResult.value ) ? 'success' : 'danger';
					$scope.alerts.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancell();
						$scope.get();
					}
				});
			}
			else {
				$http.put( URL_POPULATION + $scope.population.id, JSON.stringify($scope.population) ).success( function(data) {
					$scope.alerts.type = ( data.statusResult.value ) ? 'success' : 'danger';
					$scope.alerts.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancell();
						$scope.get();
						isUpdate = false;
					}
				});
			}
		}
		
		$scope.delete = function( population ) {
			var response = confirm( "¿Estas seguro que deseas eliminar este registro?" );
			if( response ) {
				$http.delete( URL_POPULATION + population.id ).success( function(data) {
					if( data.statusResult.value ) {
						$scope.alerts.type = ( data.statusResult.value ) ? 'success' : 'danger';
						$scope.alerts.message = data.message;
						$scope.alerts.show = true;
						$scope.get();
					}
				});
			}
		}
		
		$scope.update = function( population ) {
			$scope.population = JSON.parse( JSON.stringify( population ) );
			$scope.selectedState = $scope.population.state;
			
			console.log( $scope.population );
			console.log( $scope.selectedState );
			isUpdate = true;
		}
		
		$scope.cancell = function() {
			$scope.population.name = "";
			isUpdate = false;
			$scope.selectedState = defaultState;
		}
		
		getStates();
		$scope.get();
	}
);
