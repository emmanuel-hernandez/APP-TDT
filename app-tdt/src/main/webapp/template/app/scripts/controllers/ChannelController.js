'use strict';
/**
 * @ngdoc function
 * @name app-tdt.controller:ChannelController
 * @description
 * # ChannelController
 * Controller of the channels.html view
 */
angular.module( APP_NAME ).controller( 'ChannelController',
	function($scope, $position, $http) {
		const URL_CHANNEL = 'http://localhost:8181/channel/';
		const URL_CHANNEL_BAND = 'http://localhost:8181/channelBand/';
		const URL_POPULATION = 'http://localhost:8181/population/';
		const URL_CONCESSIONAIRE = 'http://localhost:8181/concessionaire/';
		const URL_CONCESSION_TYPE = 'http://localhost:8181/concessionType/';
		
		$scope.channel = {
		  id: 0,
		  distinctive: null,
		  name: null,
		  virtualChannel: null,
		  physicChannel: null,
		  quality: null,
		  resolution: null,
		  power: null,
		  acesli: null,
		  latitude: null,
		  longitude: null,
		  effectiveDateStart: null,
		  effectiveDateEnd: null,
		  channelBand: null,
		  population: null,
		  concessionaire: null,
		  concessionType: null
		};
		
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
			$http.get( URL_CHANNEL_BAND ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.channelBands = data.collection;
					defaultChannelBand = $scope.channelBands[0];
					$scope.selectedChannelBand = defaultChannelBand;
				}
				else {
					$scope.alerts.show = true;
					$scope.alerts.resultMessage = data.message;
				}
				//$scope.channelBands.unshift( defaultChannelBand )
			});
		};
		var getPopulations = function() {
			$http.get( URL_POPULATION ).success( function(data) {
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
			$http.get( URL_CONCESSIONAIRE ).success( function(data) {
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
			$http.get( URL_CONCESSION_TYPE ).success( function(data) {
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
			$http.get( URL_CHANNEL ).success( function(data) {
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
				$http.post( URL_CHANNEL, JSON.stringify($scope.channel) ).success( function(data) {
					$scope.alerts.type = ( data.statusResult.value ) ? 'success' : 'danger';
					$scope.alerts.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancell();
						$scope.get();
					}
				});
			}
			else {
				$http.put( URL_CHANNEL + $scope.channel.id, JSON.stringify($scope.channel) ).success( function(data) {
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
				$http.delete( URL_CHANNEL + channel.id ).success( function(data) {
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
			$scope.channel = JSON.parse( JSON.stringify( channel ) );
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
		
		$scope.get();
	}
);
