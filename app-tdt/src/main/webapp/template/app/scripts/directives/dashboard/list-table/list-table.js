'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module( APP_NAME ).directive( 'listTable', function() {
	return { 
		templateUrl: 'scripts/directives/dashboard/list-table/list-table.html',
  		restrict: 'E',
  		replace: true,
  		scope: {
	        'collection': '=',
	        'columns': '=',
	        'updateFn': '&',
	        'deleteFn': '&'
  		}
	}
});
