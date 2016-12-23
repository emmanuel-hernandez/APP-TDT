'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module( APP_NAME ).directive( 'listTable', function( $window ) {
	return { 
		templateUrl: 'scripts/directives/dashboard/list-table/list-table.html',
  		restrict: 'E',
  		replace: true,
  		scope: {
	        collection: '=',
	        columns: '=',
	        updateFn: '&',
	        deleteFn: '&',
	        detailsFn: '&',
	        helper: '='
  		},
  		link: function( $scope, element, attrs ) {
  			console.log( $scope.helper );
  			$scope.pages = new Array();
  			for( var i=1; i<=$scope.helper.paginationAPI; i++ ) {
  				$scope.pages.push( i );
  				console.log( 'adding: ' + i );
  			}
  			
  			$scope.details = function( object ) {
  				$scope.detailsFn()( object );
  			};
  			
  			$scope.delete = function( object ) {
  				$scope.deleteFn()( object );
  				$window.scrollTo( 0, 0 );
  			};
  			
  			$scope.update = function( object ) {
  				$scope.updateFn()( object );
  				$window.scrollTo( 0, 0 );
  			};
  		}
	}
});
