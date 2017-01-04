'use strict';
/**
 * @ngdoc function
 * @name app-tdt.controller:ConcessionTypeController
 * @description
 * # ConcessionTypeController
 * Controller of the concession-type.html view
 */
angular.module( APP_NAME ).controller( 'ConcessionTypeController', ['$scope', '$http', 'httpService', function( $scope, $http, httpService ) {
		var isUpdate;
		
		var init = function() {
			$scope.alert = AlertDTO.build( null, ERROR_MESSAGE, false );
			$scope.queryHelper = QueryHelper.build( PaginationAPI.build( 1, 15, 0, 0 ),
													FilterAPI.build( null ),
													OrderAPI.build( 'type', ORDER_ASCENDING ) );
			$scope.columns = {name: [ 'Tipo', 'Descripción' ],
							  id: [ 'type', 'description' ]};
			
			$scope.concessionTypes = new Array();			
		};

		var reset = function() {
			isUpdate = false;
			$scope.alert.show = false;
			
			$scope.concessionType = ConcessionTypeDTO.build();
			$scope.concessionType.id = 0;
			$scope.concessionType.type = null;
			$scope.concessionType.description = null;
		};
		
		$scope.get = function(page, order, filter) {
			$scope.queryHelper.paginationAPI.currentPage = ( page == null || page == undefined ) ? 1 : page;
			$scope.queryHelper.orderAPI.field = (order == null || order == undefined ) ? null : $scope.columns.id[order.field];
			$scope.queryHelper.orderAPI.ascending = (order == null || order == undefined ) ? false : order.ascending;
			$scope.queryHelper.filterAPI.filter = ( filter == null || filter == undefined ) ? {name:null} : {type:filter, description:filter};
			
			var urlRequest = CONCESSION_TYPE_URL +'?'+ GET_PARAMETER_NAME +'='+ JSON.stringify( $scope.queryHelper );
			
			httpService.get( urlRequest ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.queryHelper = data.queryHelper;
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
			if( !isUpdate ) {
				httpService.post( CONCESSION_TYPE_URL, JSON.stringify($scope.concessionType) ).success( function(data) {
					$scope.alert.type = ( data.statusResult.value ) ? SUCCESS_MESSAGE : ERROR_MESSAGE;
					$scope.alert.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancel();
						$scope.get();
					}
				});
			}
			else {
				httpService.put( CONCESSION_TYPE_URL + $scope.concessionType.id, JSON.stringify($scope.concessionType) ).success( function(data) {
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
		
		$scope.delete = function( concessionType ) {
			var response = confirm( DEFAULT_DELETE_MESSAGE );
			if( response ) {
				httpService.delete( CONCESSION_TYPE_URL + concessionType.id ).success( function(data) {
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
	}]
);
