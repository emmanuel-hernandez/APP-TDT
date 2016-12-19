'use strict';
/**
 * @ngdoc function
 * @name app-tdt.controller:ConcessionTypeController
 * @description
 * # ConcessionTypeController
 * Controller of the concession-type.html view
 */
angular.module( APP_NAME ).controller( 'ConcessionTypeController',
	function($scope, $position, $http) {
		var isUpdate;
		
		var init = function() {
			$scope.alert = AlertDTO.build( null, ERROR_MESSAGE, false );
			$scope.queryHelper = QueryHelper.build( PaginationAPI.build( 1, 15, 0 ), FilterAPI.build( null ) );			
			$scope.columns = [
  				'Tipo',
  				'Descripción'
  			];
			
			$scope.concessionTypes = new Array();			
		};

		var reset = function() {
			isUpdate = false;
			$scope.alert.show = false;
			
			$scope.concessionType = ConcessionTypeDTO.build();
			$scope.concessionType.id = 0;
			$scope.concessionType.name = null;
		};
		
		$scope.get = function() {
			$http.get( CONCESSION_TYPE_URL +'?'+ GET_PARAMETER_NAME +'='+ JSON.stringify($scope.queryHelper) ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.concessionTypes = new Array();
					
					for(var i=0; i<data.collection.length; i++ ) {
						var concessionType = data.collection[ i ];
						var object = { object: concessionType, properties: new Array() };

						if( concessionType.active ) {
							object.properties.push( ValueDTO.build( concessionType.type ) );
							object.properties.push( ValueDTO.build( concessionType.description ) );
							
							$scope.concessionTypes.push( object );
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
			$scope.alert.show = true;
			
			if( !isUpdate ) {
				$http.post( CONCESSION_TYPE_URL, JSON.stringify($scope.concessionType) ).success( function(data) {
					$scope.alert.type = ( data.statusResult.value ) ? SUCCESS_MESSAGE : ERROR_MESSAGE;
					$scope.alert.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancel();
						$scope.get();
					}
				});
			}
			else {
				$http.put( CONCESSION_TYPE_URL + $scope.concessionType.id, JSON.stringify($scope.concessionType) ).success( function(data) {
					$scope.alert.type = ( data.statusResult.value ) ? SUCCESS_MESSAGE : ERROR_MESSAGE;
					$scope.alert.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancel();
						$scope.get();
						isUpdate = false;
					}
				});
			}
		}
		
		$scope.delete = function( concessionType ) {
			var response = confirm( DEFAULT_DELETE_MESSAGE );
			if( response ) {
				$http.delete( CONCESSION_TYPE_URL + concessionType.id ).success( function(data) {
					if( data.statusResult.value ) {
						$scope.alert.type = ( data.statusResult.value ) ? SUCCESS_MESSAGE : ERROR_MESSAGE;
						$scope.alert.message = data.message;
						$scope.alert.show = true;
						$scope.get();
					}
				});
			}
		}
		
		$scope.update = function( concessionType ) {
			$scope.concessionType.id = concessionType.id;
			$scope.concessionType.type = concessionType.type;
			$scope.concessionType.description = concessionType.description;
			$scope.concessionType.active = concessionType.active;
			isUpdate = true;
		}
		
		$scope.cancel = function() {
			reset();
		}
		
		init();
		reset();
		$scope.get();
	}	
);
