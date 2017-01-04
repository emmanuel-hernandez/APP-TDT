'use strict';
/**
 * @ngdoc function
 * @name app-tdt.controller:ConcessionaireController
 * @description
 * # ConcessionaireController
 * Controller of the concessionaire.html view
 */
angular.module( APP_NAME ).controller( 'ConcessionaireController', ['$scope', '$http', 'httpService', function( $scope, $http, httpService ) {
		var isUpdate;
		
		var init = function() {
			$scope.alert = AlertDTO.build( null, ERROR_MESSAGE, false );
			$scope.queryHelper = QueryHelper.build( PaginationAPI.build( 1, 15, 0, 0 ),
													FilterAPI.build( null ),
													OrderAPI.build( 'name', ORDER_ASCENDING ) );			
			$scope.columns = {name: [ 'Nombre' ],
							  id: [ 'name' ]};
			
			$scope.concessionaires = new Array();
		};

		var reset = function() {
			isUpdate = false;
			$scope.alert.show = false;
			
			$scope.concessionaire = ConcessionaireDTO.build();
			$scope.concessionaire.id = 0;
			$scope.concessionaire.name = null;
		};
		
		$scope.get = function(page, order, filter) {
			$scope.queryHelper.paginationAPI.currentPage = ( page == null || page == undefined ) ? 1 : page;
			$scope.queryHelper.orderAPI.field = (order == null || order == undefined ) ? null : $scope.columns.id[order.field];
			$scope.queryHelper.orderAPI.ascending = (order == null || order == undefined ) ? false : order.ascending;
			$scope.queryHelper.filterAPI.filter = ( filter == null || filter == undefined ) ? {name:null} : {name:filter};
			
			var urlRequest = CONCESSIONAIRE_URL +'?'+ GET_PARAMETER_NAME +'='+ JSON.stringify( $scope.queryHelper );
			
			httpService.get( urlRequest ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.queryHelper = data.queryHelper;
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
				httpService.post( CONCESSIONAIRE_URL, JSON.stringify($scope.concessionaire) ).success( function(data) {
					$scope.alert.type = ( data.statusResult.value ) ? SUCCESS_MESSAGE : ERROR_MESSAGE;
					$scope.alert.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancel();
						$scope.get();
					}
				});
			}
			else {
				httpService.put( CONCESSIONAIRE_URL + $scope.concessionaire.id, JSON.stringify($scope.concessionaire) ).success( function(data) {
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
				httpService.delete( CONCESSIONAIRE_URL + concessionaire.id ).success( function(data) {
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
