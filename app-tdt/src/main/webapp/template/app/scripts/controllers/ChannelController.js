'use strict';
/**
 * @ngdoc function
 * @name app-tdt.controller:ChannelController
 * @description
 * # ChannelController
 * Controller of the channels.html view
 */
angular.module( APP_NAME ).controller( 'ChannelController',
	function($scope, $position, $http, DTOptionsBuilder, DTColumnBuilder) {
		$scope.channel = getInstance( ChannelDTO );
		$scope.queryHelper = getInstance( QueryHelper );
		$scope.queryHelper.paginationAPI.page = 1;
		$scope.queryHelper.paginationAPI.pageSize = 10;
		$scope.queryHelper.filterAPI = null;
		$scope.dtColumns =[
		                   DTColumnBuilder.newColumn('id').withTitle('ID'),
		                   DTColumnBuilder.newColumn('distinctive').withTitle('Distintivo')
		];
		$scope.dtOptions = DTOptionsBuilder.newOptions().withOption('ajax', {
			url: CHANNEL_URL +'?queryHelper='+ JSON.stringify($scope.queryHelper),
			type: 'GET',
			dataSrc: 'collection'
		}).withPaginationType('full_numbers');
		
		var defaultPhysicChannel = { id: 1, name: 14 };
		var defaultResolution = { id: 1, name: '1080i' };
		var defaultQuality = { id: 1, name: 'HD' };
		var defaultResolution = { id: 1, name: '1080i' };
		var defaultChannelBand = { id: -1, name: 'Seleccionar...' };
		var defaultPopulation = { id: -1, name: 'Seleccionar...' };
		var defaultConcessionaire = { id: -1, name: 'Seleccionar...' };
		var defaultConcessionType = { id: -1, name: 'Seleccionar...' };
		
		$scope.selectedPhysicChannel = defaultPhysicChannel;
		$scope.selectedQuality = defaultQuality;
		$scope.selectedResolution = defaultResolution;
		$scope.selectedChannelBand = defaultChannelBand;
		$scope.selectedPopulation = defaultPopulation;
		$scope.selectedConcessionaire = defaultConcessionaire;
		$scope.selectedConcessionType = defaultConcessionType;

		var isUpdate = false;
		
		$scope.physicChannels = new Array();
		for( var i=14; i<=84; i++ ) {
			$scope.physicChannels.push({ id: (i-13), name: i });
		}
		
		$scope.qualities = [{id: 1, name: 'HD'}, {id: 2, name: 'SD'}];
		$scope.resolutions = [{id: 1, name: '1080i'}, {id: 2, name: '480i'}];
		$scope.channels = new Array();
		$scope.channelBands = new Array();
		$scope.populations = new Array();
		$scope.concessionaires = new Array();
		$scope.concessionTypes = new Array();
		
		$scope.alerts = {
			message: '',
			type: '',
			show: false
		};

		var getChannelBands = function() {
			$http.get( CHANNEL_BAND_URL ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.channelBands = data.collection;
					defaultChannelBand = $scope.channelBands[0];
					$scope.selectedChannelBand = defaultChannelBand;
				}
				else {
					$scope.alerts.show = true;
					$scope.alerts.resultMessage = data.message;
				}
			});
		};
		var getPopulations = function() {
			$http.get( POPULATION_URL ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.populations = data.collection;
				}
				else {
					$scope.alerts.show = true;
					$scope.alerts.resultMessage = data.message;
				}
				$scope.populations.unshift( defaultPopulation );
			});
		};
		var getConcessionaires = function() {
			$http.get( CONCESSIONAIRE_URL ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.concessionaires = data.collection;
				}
				else {
					$scope.alerts.show = true;
					$scope.alerts.resultMessage = data.message;
				}
				$scope.concessionaires.unshift( defaultConcessionaire );
			});
		};
		var getConcessionTypes = function() {
			$http.get( CONCESSION_TYPE_URL ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.concessionTypes = data.collection;
				}
				else {
					$scope.alerts.show = true;
					$scope.alerts.resultMessage = data.message;
				}
				$scope.concessionTypes.unshift( defaultConcessionType );
			});
		};
		
		$scope.get = function() {
			$http.get( CHANNEL_URL +'?queryHelper='+ JSON.stringify($scope.queryHelper) ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.channels = data.collection;
				}
				else {
					$scope.alerts.show = true;
					$scope.alerts.resultMessage = data.message;
				}
			});
		}
		
		$scope.save = function() {
			$scope.alerts.show = true;
			
			for( var i=0; i<$scope.channelBands.length; i++ ) {
				if( $scope.channelBands[i].id == $scope.selectedChannelBand.id ) {
					$scope.channel.channelBand = $scope.channelBands[i];
					break;
				}
			}
			for( var i=0; i<$scope.populations.length; i++ ) {
				if( $scope.populations[i].id == $scope.selectedPopulation.id ) {
					$scope.channel.population = $scope.populations[i];
					break;
				}
			}
			for( var i=0; i<$scope.concessionaires.length; i++ ) {
				if( $scope.concessionaires[i].id == $scope.selectedConcessionaire.id ) {
					$scope.channel.concessionaire = $scope.concessionaires[i];
					break;
				}
			}
			for( var i=0; i<$scope.concessionTypes.length; i++ ) {
				if( $scope.concessionTypes[i].id == $scope.selectedConcessionType.id ) {
					$scope.channel.concessionType = $scope.concessionTypes[i];
					break;
				}
			}
			
			if( !isUpdate ) {
				$http.post( CHANNEL_URL, JSON.stringify($scope.channel) ).success( function(data) {
					$scope.alerts.type = ( data.statusResult.value ) ? 'success' : 'danger';
					$scope.alerts.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancell();
						$scope.get();
					}
				});
			}
			else {
				$http.put( CHANNEL_URL + $scope.channel.id, JSON.stringify($scope.channel) ).success( function(data) {
					$scope.alerts.type = ( data.statusResult.value ) ? 'success' : 'danger';
					$scope.alerts.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancell();
						$scope.get();
						isUpdate = false;
					}
				});
			}
		}
		
		$scope.delete = function( channel ) {
			var response = confirm( "¿Estas seguro que deseas eliminar este registro?" );
			if( response ) {
				$http.delete( CHANNEL_URL + channel.id ).success( function(data) {
					if( data.statusResult.value ) {
						$scope.alerts.type = ( data.statusResult.value ) ? 'success' : 'danger';
						$scope.alerts.message = data.message;
						$scope.alerts.show = true;
						$scope.get();
					}
				});
			}
		}
		
		$scope.update = function( channel ) {
			$scope.channel = getInstance( channel );
			$scope.selectedChannelBand = $scope.channel.channelBand;
			$scope.selectedPopulation = $scope.channel.population;
			$scope.selectedConcessionaire = $scope.channel.concessionaire;
			$scope.selectedConcessionType = $scope.channel.concessionType;
			//$scope.selectedQuality = $scope.channel.;
			//$scope.selectedPhysicChannel = $scope.channel.concessionType;
			
			isUpdate = true;
		}
		
		$scope.cancell = function() {
			$scope.channel.name = "";
			isUpdate = false;
			
			$scope.selectedQuality = defaultQuality;
			$scope.selectedPhysicChannel = defaultPhysicChannel;
			$scope.selectedChannelBand = defaultChannelBand;
			$scope.selectedPopulation = defaultPopulation;
			$scope.selectedConcessionaire = defaultConcessionaire;
			$scope.selectedConcessionType = defaultConcessionType;
		}
		
		getChannelBands();
		getPopulations();
		getConcessionaires();
		getConcessionTypes();
		
		//$scope.get();
	}
);
