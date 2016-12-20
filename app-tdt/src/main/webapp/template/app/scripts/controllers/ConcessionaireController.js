'use strict';
/**
 * @ngdoc function
 * @name app-tdt.controller:ConcessionaireController
 * @description
 * # ConcessionaireController
 * Controller of the concessionaire.html view
 */
angular.module( APP_NAME ).controller( 'ConcessionaireController', ['$scope', '$http',
	function($scope, $http) {
		var isUpdate;
		
		var init = function() {
			$scope.alert = AlertDTO.build( null, ERROR_MESSAGE, false );
			$scope.queryHelper = QueryHelper.build( PaginationAPI.build( 1, 15, 0 ),
													FilterAPI.build( null ),
													OrderAPI.build( 'name', ORDER_ASCENDING ) );			
			$scope.columns = [
  				'Nombre',
  			];
			
			$scope.concessionaires = new Array();
		};

		var reset = function() {
			isUpdate = false;
			
			$scope.concessionaire = ConcessionaireDTO.build();
			$scope.concessionaire.id = 0;
			$scope.concessionaire.name = null;
		};
		
		$scope.get = function() {
			$http.get( CONCESSIONAIRE_URL +'?'+ GET_PARAMETER_NAME +'='+ JSON.stringify($scope.queryHelper) ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.concessionaires = new Array();
					
					for(var i=0; i<data.collection.length; i++ ) {
						var concessionaire = data.collection[ i ];
						var object = { object: concessionaire, properties: new Array() };

						if( concessionaire.active ) {
							object.properties.push( ValueDTO.build( concessionaire.name ) );
							
							$scope.concessionaires.push( object );
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
			if( !isUpdate ) {
				$http.post( CONCESSIONAIRE_URL, JSON.stringify($scope.concessionaire) ).success( function(data) {
					$scope.alert.type = ( data.statusResult.value ) ? SUCCESS_MESSAGE : ERROR_MESSAGE;
					$scope.alert.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancel();
						$scope.get();
					}
				});
			}
			else {
				$http.put( CONCESSIONAIRE_URL + $scope.concessionaire.id, JSON.stringify($scope.concessionaire) ).success( function(data) {
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
		
		$scope.delete = function( concessionaire ) {
			var response = confirm( DEFAULT_DELETE_MESSAGE );
			if( response ) {
				$http.delete( CONCESSIONAIRE_URL + concessionaire.id ).success( function(data) {
					if( data.statusResult.value ) {
						$scope.alert.type = ( data.statusResult.value ) ? SUCCESS_MESSAGE : ERROR_MESSAGE;
						$scope.alert.message = data.message;
						$scope.alert.show = true;
						$scope.get();
					}
				});
			}
		}
		
		$scope.update = function( concessionaire ) {
			$scope.concessionaire.id = concessionaire.id;
			$scope.concessionaire.name = concessionaire.name;
			$scope.concessionaire.active = concessionaire.active;
			isUpdate = true;
		}
		
		$scope.cancel = function() {
			reset();
		}
		
		init();
		reset();
		$scope.get();
	}]
);
