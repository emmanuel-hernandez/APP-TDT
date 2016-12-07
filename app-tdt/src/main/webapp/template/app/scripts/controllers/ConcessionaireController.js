'use strict';
/**
 * @ngdoc function
 * @name app-tdt.controller:ConcessionaireController
 * @description
 * # ConcessionaireController
 * Controller of the concessionaire.html view
 */
angular.module( APP_NAME ).controller( 'ConcessionaireController',
	function($scope, $position, $http) {
		$scope.concessionaire = {
			id: 0,
			name: null
		};
		var isUpdate = false;
		$scope.concessionaires = new Array();
		$scope.alerts = {
			message: '',
			type: '',
			show: false
		};
		
		$scope.get = function() {
			$http.get( 'http://localhost:8181/concessionaire/' ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.concessionaires = data.collection;
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
				$http.post( 'http://localhost:8181/concessionaire/', JSON.stringify($scope.concessionaire) ).success( function(data) {
					$scope.alerts.type = ( data.statusResult.value ) ? 'success' : 'danger';
					$scope.alerts.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancell();
						$scope.get();
					}
				});
			}
			else {
				$http.put( 'http://localhost:8181/concessionaire/' + $scope.concessionaire.id, JSON.stringify($scope.concessionaire) ).success( function(data) {
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
		
		$scope.delete = function( concessionaire ) {
			var response = confirm( "¿Estas seguro que deseas eliminar este registro?" );
			if( response ) {
				$http.delete( 'http://localhost:8181/concessionaire/' + concessionaire.id ).success( function(data) {
					if( data.statusResult.value ) {
						$scope.alerts.type = ( data.statusResult.value ) ? 'success' : 'danger';
						$scope.alerts.message = data.message;
						$scope.alerts.show = true;
						$scope.get();
					}
				});
			}
		}
		
		$scope.update = function( concessionaire ) {
			$scope.concessionaire = JSON.parse( JSON.stringify( concessionaire ) );
			isUpdate = true;
		}
		
		$scope.cancell = function() {
			$scope.concessionaire.name = "";
			isUpdate = false;
		}
		
		$scope.get();
	}	
);
