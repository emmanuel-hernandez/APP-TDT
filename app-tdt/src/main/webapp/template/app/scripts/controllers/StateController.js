'use strict';
/**
 * @ngdoc function
 * @name app-tdt.controller:StateController
 * @description
 * # StateController
 * Controller of the state.html view
 */
angular.module( APP_NAME ).controller( 'StateController', ['$scope', '$http', 'httpService', function( $scope, $http, httpService ) {
		var isUpdate;

		var init = function() {
			$scope.alert = AlertDTO.build( null, ERROR_MESSAGE, false );
			$scope.queryHelper = QueryHelper.build( PaginationAPI.build( 1, 15, 0, 0 ),
													FilterAPI.build( null ),
													OrderAPI.build( 'name', ORDER_ASCENDING ) );			
			$scope.columns = {
				name: [ 'Nombre', 'Abreviatura' ],
				id: [ 'name', 'shortName' ]
			};
			$scope.states = new Array();
		};
		
		var reset = function() {
			isUpdate = false;
			
			$scope.state = StateDTO.build();
			$scope.state.id = 0;
			$scope.state.name = null;
			$scope.state.shortName = null;
			$scope.alert.show = false;
		};
		
		$scope.get = function(page, order, filter) {
			$scope.queryHelper.paginationAPI.currentPage = ( page == null || page == undefined ) ? 1 : page;
			$scope.queryHelper.orderAPI.field = (order == null || order == undefined ) ? null : $scope.columns.id[order.field];
			$scope.queryHelper.orderAPI.ascending = (order == null || order == undefined ) ? false : order.ascending;
			$scope.queryHelper.filterAPI.filter = ( filter == null || filter == undefined ) ? {name:null} : {name:filter, shortName:filter};
			
			var urlRequest = STATE_URL +'?'+ GET_PARAMETER_NAME +'='+ JSON.stringify( $scope.queryHelper );
			
			httpService.get( urlRequest ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.queryHelper = data.queryHelper;
					$scope.states = new Array();
					
					for(var i=0; i<data.collection.length; i++ ) {
						var state = data.collection[ i ];
						var object = { object: state, properties: new Array() };

						if( state.active ) {
							object.properties.push( ValueDTO.build( state.name ) );
							object.properties.push( ValueDTO.build( state.shortName ) );
							
							$scope.states.push( object );
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
			console.log( JSON.stringify($scope.state) );
			if( !isUpdate ) {
				httpService.post( STATE_URL, JSON.stringify($scope.state) ).success( function(data) {
					$scope.alert.type = ( data.statusResult.value ) ? SUCCESS_MESSAGE : ERROR_MESSAGE;
					$scope.alert.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancel();
						$scope.get();
					}
				});
			}
			else {
				httpService.put( STATE_URL + $scope.state.id, JSON.stringify($scope.state) ).success( function(data) {
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
		
		$scope.delete = function( state ) {
			var response = confirm( DEFAULT_DELETE_MESSAGE );
			if( response ) {
				httpService.delete( STATE_URL + state.id ).success( function(data) {
					if( data.statusResult.value ) {
						$scope.alert.type = ( data.statusResult.value ) ? SUCCESS_MESSAGE : ERROR_MESSAGE;
						$scope.alert.message = data.message;
						$scope.alert.show = true;
						$scope.get();
					}
				});
			}
		}
		
		$scope.update = function( state ) {
			$scope.state = new StateDTO.build();
			$scope.state.id = state.id;
			$scope.state.name = state.name;
			$scope.state.shortName = state.shortName;
			$scope.state.active = state.active;
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
