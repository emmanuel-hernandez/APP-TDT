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
	        helper: '=',
	        queryFn: '&',
	        hasDetails: '=',
	        isCrud: '=?',
	        hasFilter: '=?'
  		},
  		link: function( $scope, element, attrs ) {
  			var isAscending = true;
  			  			
  			if( $scope.isCrud == null || $scope.isCrud == undefined ) {
  				$scope.isCrud = true;
  			}
  			
  			if( $scope.hasFilter == null || $scope.hasFilter == undefined ) {
  				$scope.hasFilter = true;
  			}
  			
  			if( $scope.helper != null || $scope.helper != undefined ) {
  				$scope.$watch( 'helper', function(newValue, oldValue, scope) {
  	  				$scope.currentPage = $scope.helper.paginationAPI.currentPage;
  	  				$scope.lastPage = $scope.helper.paginationAPI.totalPages;
  	  				
  	  				var currentPage = $scope.helper.paginationAPI.currentPage;
  	  				var totalPages = $scope.helper.paginationAPI.totalPages;
  	  				var pages = 0;
  	  				var totalPagination = LEFT_HAND_SIDE_PAGES + RIGHT_HAND_SIDE_PAGES;
  	  				$scope.pages = new Array();
  	  				
  	  				if( totalPagination >= totalPages ) {
  	  					for( var page=1; page<=totalPages; page++ ) {
  							$scope.pages.push( page );
  						}
  	  				}
  	  				else {
  		  				if( currentPage <= LEFT_HAND_SIDE_PAGES ) {
  		  					pages = totalPages - totalPagination; 
  		  					pages = pages <= 0 ? currentPage : totalPagination + 1;
  							for( var page=1; page<=pages; page++ ) {
  								$scope.pages.push( page );
  							}
  		  				}
  		  				else {
  		  					var leftPages = currentPage - LEFT_HAND_SIDE_PAGES;
  		  					var rightPages = currentPage + RIGHT_HAND_SIDE_PAGES;
  		  					rightPages = rightPages <= totalPages ? rightPages : totalPages;
  		  					
  		  					for( var page=leftPages; page<=rightPages; page++ ) {
  		  						$scope.pages.push( page );
  		  					}
  		  				}
  	  				}
  	  			});
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
  			
  			$scope.navigateTo = function( pageNumber ) {
  				$scope.queryFn()( pageNumber, null, null );
  			};
  			
  			$scope.order = function( header ) {
  				isAscending = !isAscending;
  				$scope.queryFn()( 1, {field:header, ascending:isAscending}, $scope.filterText );
  			};
  			
  			$scope.filter = function() {
  				$scope.filterText = $scope.filterText.length > 0 ? $scope.filterText : null; 
  				$scope.queryFn()( 1, null, $scope.filterText );
  			};
  			
  			$scope.keyEvent = function( event ) {
  				if( event.keyCode == KEY_CODE.INTRO_KEY ) {
  					$scope.filter();
  				}
  			};
  		}
	}
});
