'use strict';
/**
 * @ngdoc function
 * @name app-tdt.controller:PopulationController
 * @description
 * # PopulationController
 * Controller of the population.html view
 */
angular.module( APP_NAME ).controller( 'PopulationController', ['$scope', '$http',
	function($scope, $http) {
		var isUpdate;
		
		var init = function() {
			$scope.alert = AlertDTO.build( null, ERROR_MESSAGE, false );
			$scope.queryHelper = QueryHelper.build( PaginationAPI.build( 1, 15, 0 ),
													FilterAPI.build( null ),
													OrderAPI.build( 'name', ORDER_ASCENDING ) );
			$scope.columns = [
				'Nombre',
				'Estado'
			];
	
			$scope.populations = new Array();
			$scope.states = new Array();
		}
		
		var reset = function() {
			isUpdate = false;
			
			$scope.population = PopulationDTO.build();
			$scope.population.id = 0;
			$scope.population.name = null;
			$scope.population.state = null;
			$scope.selectedState = DEFAULT_SELECTED_VALUE;
		};

		var getStates = function() {
			$http.get( STATE_URL ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.states = data.collection;
				}
				else {
					$scope.alert.show = true;
					$scope.alert.message = data.message;
				}
			});
		};
		
		$scope.get = function() {
			$http.get( POPULATION_URL +'?'+ GET_PARAMETER_NAME +'='+ JSON.stringify($scope.queryHelper) ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.populations = new Array();

					for(var i=0; i<data.collection.length; i++ ) {
						var population = data.collection[ i ];

						if( population.active ) {
							var object = { object: population, properties: new Array() };
							
							object.properties.push( ValueDTO.build( population.name ) );
							object.properties.push( ValueDTO.build( population.state.name ) );
							
							$scope.populations.push( object );
						}						
					}
				}
				else {
					$scope.alert.message = data.message;
					$scope.alert.show = true;
				}
			});
		}
		
		$scope.save = function() {
			for( var i=0; i<$scope.states.length; i++ ) {
				if( $scope.states[i].id == $scope.selectedState ) {
					$scope.population.state = $scope.states[i];
					break;
				}
			}
			
			if( !isUpdate ) {
				$http.post( POPULATION_URL, JSON.stringify($scope.population) ).success( function(data) {
					$scope.alert.type = ( data.statusResult.value ) ? SUCCESS_MESSAGE : ERROR_MESSAGE;
					$scope.alert.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancel();
						$scope.get();
					}
				});
			}
			else {
				$http.put( POPULATION_URL + $scope.population.id, JSON.stringify($scope.population) ).success( function(data) {
					$scope.alert.type = ( data.statusResult.value ) ? SUCCESS_MESSAGE : ERROR_MESSAGE;
					$scope.alert.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancel();
						$scope.get();
						isUpdate = false;
					}
				});
			}

			$scope.alert.show = true;
		}
		
		$scope.delete = function( population ) {
			var response = confirm( DEFAULT_DELETE_MESSAGE );
			if( response ) {
				$http.delete( POPULATION_URL + population.id ).success( function(data) {
					if( data.statusResult.value ) {
						$scope.alert.type = ( data.statusResult.value ) ? SUCCESS_MESSAGE : ERROR_MESSAGE;
						$scope.alert.message = data.message;
						$scope.alert.show = true;
						$scope.get();
					}
				});
			}
		}
		
		$scope.update = function( population ) {
			$scope.population = PopulationDTO.build();
			$scope.population.id = population.id;
			$scope.population.name = population.name;
			$scope.population.state = population.state;
			$scope.selectedState = population.state.id;
			$scope.population.active = population.active;
			isUpdate = true;
		}
		
		$scope.cancel = function() {
			reset();
		}
		
		/* Initialization */
		init();
		reset();
		getStates();
		$scope.get();
	}]
);
