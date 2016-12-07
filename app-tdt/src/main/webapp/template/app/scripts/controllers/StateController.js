'use strict';
/**
 * @ngdoc function
 * @name app-tdt.controller:StateController
 * @description
 * # StateController
 * Controller of the state.html view
 */
angular.module( APP_NAME ).controller( 'StateController',
	function($scope, $position, $http) {
		$scope.state = {
			id: 0,
			name: null,
			shortName: null
		};
		var isUpdate = false;
		$scope.states = new Array();
		$scope.alerts = {
			message: '',
			type: '',
			show: false
		};
		
		$scope.get = function() {
			$http.get( 'http://localhost:8181/state/' ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.states = data.collection;
				}
				else {
					$scope.alerts.show = true;
					$scope.alerts.resultMessage = data.message;
				}
			});
		}
		
		$scope.save = function() {
			$scope.alerts.show = true;
			if( !isUpdate ) {
				$http.post( 'http://localhost:8181/state/', JSON.stringify($scope.state) ).success( function(data) {
					$scope.alerts.type = ( data.statusResult.value ) ? 'success' : 'danger';
					$scope.alerts.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancell();
						$scope.get();
					}
				});
			}
			else {
				$http.put( 'http://localhost:8181/state/' + $scope.state.id, JSON.stringify($scope.state) ).success( function(data) {
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
		
		$scope.delete = function( state ) {
			var response = confirm( "¿Estas seguro que deseas eliminar este registro?" );
			if( response ) {
				$http.delete( 'http://localhost:8181/state/' + state.id ).success( function(data) {
					if( data.statusResult.value ) {
						$scope.alerts.type = ( data.statusResult.value ) ? 'success' : 'danger';
						$scope.alerts.message = data.message;
						$scope.alerts.show = true;
						$scope.get();
					}
				});
			}
		}
		
		$scope.update = function( state ) {
			$scope.state = JSON.parse( JSON.stringify( state ) );
			isUpdate = true;
		}
		
		$scope.cancell = function() {
			$scope.state.name = "";
			$scope.state.shortName = "";
			isUpdate = false;
		}
		
		$scope.get();
	}	
);
