'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('sbAdminApp').directive( 'tableList', function() {
	return {
  		templateUrl: 'scripts/directives/dashboard/table/table.html',
  		restrict: 'E',
  		replace:true,
  		scope: {
  			'headers': '=',
  			'collection': '='
  		}  		
  	}
});