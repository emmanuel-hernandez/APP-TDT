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
		$scope.state = null;
		$scope.states = new Array();
		
		$http.get( 'http://localhost:8181/state/' ).success( function(data) {
			if( data.statusResult.value ) {
				$scope.states = data.collection;
			}
		});
		
		$scope.save = function() {
			var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            };
			
			var state = $.param({
                id: 0,
                name: $scope.state.name,
                shortName: $scope.state.shortName
            });

			$http.post( 'http://localhost:8181/state/', {"state": JSON.stringify(state)}, config ).success( function(data) {
				if( data.statusResult.value ) {
					alert( data.message );
				}
				else {
					console.log( data );
				}
			});
		}
		
		$scope.delete = function( state ) {
			var c = confirm( "¿Estas seguro que deseas eliminar este registro?" );
			if( c) {
				alert( 'Registro eliminado' );
			}
		}
		
		$scope.update = function( state ) {
			$scope.state = state;
		}
		
		$scope.cancell = function() {
			$scope.name = "";
			$scope.shortName = "";
		}
	}	
);
