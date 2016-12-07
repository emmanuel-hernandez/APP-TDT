'use strict';

/**
 * @ngdoc directive
 * @name app-tdt.directive:alerts
 * @description
 * # alerts
 */
angular.module( APP_NAME )
	.directive('alertInfo',function(){
		return {
	        templateUrl:'scripts/directives/alerts/alerts.html',
	        restrict: 'E',
	        replace: true,
	        scope: {
	        	message: "@",
	            type: "@"
	        }
    	}
	});


