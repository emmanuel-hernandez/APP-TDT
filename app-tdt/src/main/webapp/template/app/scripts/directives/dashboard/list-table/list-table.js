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
	        deleteFn: '&'
  		},
  		link: function( $scope, element, attrs ) {
  			$scope.showDetails = function( object ) {
  				$scope.updateFn()(object);
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
