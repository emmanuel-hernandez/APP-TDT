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
		var isUpdate;		
		var init = function() {
			isUpdate = false;
			
			$scope.queryHelper = getInstance( QueryHelper );
			$scope.queryHelper.paginationAPI.page = 1;
			$scope.queryHelper.paginationAPI.pageSize = 15;
			$scope.queryHelper.filterAPI = null;
			
			$scope.qualities = [new QualityDTO( 1, 'HD' ), new QualityDTO( 2, 'SD' )];
			$scope.resolutions = [new ResolutionDTO( 1, '1080i' ), new ResolutionDTO( 2, '480i' )];
			$scope.channels = new Array();
			$scope.channelBands = new Array();
			$scope.populations = new Array();
			$scope.concessionaires = new Array();
			$scope.concessionTypes = new Array();
			$scope.physicChannels = new Array();
			
			for( var i=14; i<=84; i++ ) {
				$scope.physicChannels.push({ id: (i-13), name: i });
			}
			
			$scope.alerts = {
				message: '',
				type: '',
				show: false
			};
			
			$scope.columns = [
				'Distintivo',
				'Nombre',
				'Canal virtual',
				'Canal físico',
				'Calidad',
				'Resolución',
				'Banda'
			];
		};

		var reset = function() {
			isUpdate = false;

			$scope.channel = new ChannelDTO();
			$scope.channel.setId( 0 );
			$scope.channel.setDistinctive( null );
			$scope.channel.setName( null );
			$scope.channel.setVirtualChannel( 0 );
			$scope.channel.setPhysicChannel( 14 );
			$scope.channel.setQuality( 1 );
			$scope.channel.setResolution( 1 );
			$scope.channel.setPower( 0 );
			$scope.channel.setAcesli( 0 );
			$scope.channel.setLongitude( 0 );
			$scope.channel.setLatitude( 0 );
			$scope.channel.setEffectiveDateStart();
			$scope.channel.setEffectiveDateEnd();
			$scope.channel.setChannelBand( 1 );
			$scope.channel.setPopulation( -1 );
			$scope.channel.setConcessionaire( -1 );
			$scope.channel.setConcessionType( -1 );
		};

		var getChannelBands = function() {
			$http.get( CHANNEL_BAND_URL ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.channelBands = data.collection;
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
			});
		};
		
		$scope.get = function() {
			$http.get( CHANNEL_URL +'?queryHelper='+ JSON.stringify($scope.queryHelper) ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.channels = new Array();
					
					for(var i=0; i<data.collection.length; i++ ) {
						var channel = data.collection[ i ];
						var object = { object: channel,
									   properties: new Array() };
						
						object.properties.push( new KeyValueDTO( i, channel.distinctive ) );
						object.properties.push( new KeyValueDTO( i, channel.name ) );
						object.properties.push( new KeyValueDTO( i, channel.virtualChannel ) );
						object.properties.push( new KeyValueDTO( i, channel.physicChannel ) );
						object.properties.push( new KeyValueDTO( i, channel.quality ) );
						object.properties.push( new KeyValueDTO( i, channel.resolution ) );
						object.properties.push( new KeyValueDTO( i, channel.channelBand.name ) );
						
						$scope.channels.push( object );
					}			
				}
				else {
					$scope.alerts.show = true;
					$scope.alerts.resultMessage = data.message;
				}
			});
		}
		
		$scope.save = function() {
			$scope.alerts.show = true;
			console.log( $scope.channel );
			return;
			
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
						$scope.cancel();
						$scope.get();
					}
				});
			}
			else {
				$http.put( CHANNEL_URL + $scope.channel.id, JSON.stringify($scope.channel) ).success( function(data) {
					$scope.alerts.type = ( data.statusResult.value ) ? 'success' : 'danger';
					$scope.alerts.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancel();
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
			$scope.channel = new ChannelDTO();
			$scope.channel.setId( channel.id );
			$scope.channel.setDistinctive( channel.distinctive );
			$scope.channel.setName( channel.name );
			$scope.channel.setVirtualChannel( channel.virtualChannel );
			$scope.channel.setPhysicChannel( channel.physicChannel - 13 );
			$scope.channel.setQuality( (channel.quality === 'HD') ? 1 : 2 );
			$scope.channel.setResolution( (channel.resolution === '1080i') ? 1 : 2 );
			$scope.channel.setPower( channel.power );
			$scope.channel.setAcesli( channel.acesli );
			$scope.channel.setLongitude( channel.longitud );
			$scope.channel.setLatitude( channel.latitude );
			$scope.channel.setEffectiveDateStart( channel.effectiveDayStart );
			$scope.channel.setEffectiveDateEnd( channel.effectiveDayEnd );
			$scope.channel.setChannelBand( channel.channelBand.id );
			$scope.channel.setPopulation( channel.population.id );
			$scope.channel.setConcessionaire( channel.concessionaire.id );
			$scope.channel.setConcessionType( channel.concessionType.id );
			isUpdate = true;
		}
		
		$scope.cancel = function() {
			reset();
		}

		/* Initialization */
		init();
		reset();
		getChannelBands();
		getPopulations();
		getConcessionaires();
		getConcessionTypes();
		
		$scope.get();
	}
);
