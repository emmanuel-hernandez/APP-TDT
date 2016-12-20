'use strict';
/**
 * @ngdoc function
 * @name app-tdt.controller:ChannelController
 * @description
 * # ChannelController
 * Controller of the channels.html view
 */
angular.module( APP_NAME ).controller( 'ChannelController', ['$scope', '$http',
	function($scope, $http) {
		var isUpdate;
		
		var init = function() {
			$scope.alert = AlertDTO.build( null, ERROR_MESSAGE, false );
			$scope.queryHelper = QueryHelper.build( PaginationAPI.build( 1, 15, 0 ),
													FilterAPI.build( null ),
													OrderAPI.build( 'distinctive', ORDER_ASCENDING ) );			
			$scope.columns = [
  				'Distintivo',
  				'Nombre',
  				'Canal virtual',
  				'Canal físico',
  				'Calidad',
  				'Resolución',
  				'Banda'
  			];
			$scope.qualities = [QualityDTO.build( CHANNEL_QUALITY.SD.ID, CHANNEL_QUALITY.SD.VALUE ),
			                    QualityDTO.build( CHANNEL_QUALITY.HD.ID, CHANNEL_QUALITY.HD.VALUE )];
			$scope.resolutions = [ResolutionDTO.build( CHANNEL_RESOLUTION.SD.ID, CHANNEL_RESOLUTION.SD.VALUE ),
			                      ResolutionDTO.build( CHANNEL_RESOLUTION.FULL.ID, CHANNEL_RESOLUTION.FULL.VALUE )];
			$scope.channels = new Array();
			$scope.channelBands = new Array();
			$scope.populations = new Array();
			$scope.concessionaires = new Array();
			$scope.concessionTypes = new Array();
			$scope.physicChannels = new Array();
			
			for( var i=FIRST_PHYSIC_CHANNEL; i<=LAST_PHYSIC_CHANNEL; i++ ) {
				$scope.physicChannels.push({ id: (i-13), name: i });
			}
		};

		var reset = function() {
			isUpdate = false;
			
			$scope.channel = ChannelDTO.build();
			$scope.channel.id = 0;
			$scope.channel.distinctive = null;
			$scope.channel.name = null;
			$scope.channel.virtualChannel = 0;
			$scope.channel.physicChannel = FIRST_PHYSIC_CHANNEL;
			$scope.channel.quality = CHANNEL_QUALITY.SD.ID;
			$scope.channel.resolution = CHANNEL_RESOLUTION.SD.ID;
			$scope.channel.power = 0;
			$scope.channel.acesli = 0;
			$scope.channel.longitude = 0;
			$scope.channel.latitude = 0;
			$scope.channel.effectiveDateStart = null;
			$scope.channel.effectiveDateEnd = null;
			$scope.channel.channelBand = 1;
			$scope.channel.population = null;
			$scope.channel.concessionaire = null;
			$scope.channel.concessionType = null;
			$scope.selectedChannelBand = 1;
			$scope.selectedPopulation = DEFAULT_SELECTED_VALUE;
			$scope.selectedConcessionaire = DEFAULT_SELECTED_VALUE;
			$scope.selectedConcessionType = DEFAULT_SELECTED_VALUE;
		};

		var getChannelBands = function() {
			$http.get( CHANNEL_BAND_URL ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.channelBands = data.collection;
				}
				else {
					$scope.alert.message = data.message;
					$scope.alert.show = true;
				}
			});
		};
		var getPopulations = function() {
			$http.get( POPULATION_URL ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.populations = data.collection;
				}
				else {
					$scope.alert.message = data.message;
					$scope.alert.show = true;
				}
			});
		};
		var getConcessionaires = function() {
			$http.get( CONCESSIONAIRE_URL ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.concessionaires = data.collection;
				}
				else {
					$scope.alert.message = data.message;
					$scope.alert.show = true;
				}
			});
		};
		var getConcessionTypes = function() {
			$http.get( CONCESSION_TYPE_URL ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.concessionTypes = data.collection;
				}
				else {
					$scope.alert.message = data.message;
					$scope.alert.show = true;
				}
			});
		};

		$scope.get = function() {
			$http.get( CHANNEL_URL +'?'+ GET_PARAMETER_NAME +'='+ JSON.stringify($scope.queryHelper) ).success( function(data) {
				if( data.statusResult.value ) {
					$scope.channels = new Array();

					for(var i=0; i<data.collection.length; i++ ) {
						var channel = data.collection[ i ];
						var object = { object: channel, properties: new Array() };

						if( channel.active ) {
							object.properties.push( ValueDTO.build( channel.distinctive ) );
							object.properties.push( ValueDTO.build( channel.name ) );
							object.properties.push( ValueDTO.build( channel.virtualChannel ) );
							object.properties.push( ValueDTO.build( channel.physicChannel ) );
							object.properties.push( ValueDTO.build( channel.quality ) );
							object.properties.push( ValueDTO.build( channel.resolution ) );
							object.properties.push( ValueDTO.build( channel.channelBand.name ) );
							
							$scope.channels.push( object );
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
			for( var i=0; i<$scope.channelBands.length; i++ ) {
				if( $scope.channelBands[i].id == $scope.channel.channelBand ) {
					$scope.channel.channelBand = $scope.channelBands[i];
					break;
				}
			}
			for( var i=0; i<$scope.populations.length; i++ ) {
				if( $scope.populations[i].id == $scope.channel.population ) {
					$scope.channel.population = $scope.populations[i];
					break;
				}
			}
			for( var i=0; i<$scope.concessionaires.length; i++ ) {
				if( $scope.concessionaires[i].id == $scope.channel.concessionaire ) {
					$scope.channel.concessionaire = $scope.concessionaires[i];
					break;
				}
			}
			for( var i=0; i<$scope.concessionTypes.length; i++ ) {
				if( $scope.concessionTypes[i].id == $scope.channel.concessionType ) {
					$scope.channel.concessionType = $scope.concessionTypes[i];
					break;
				}
			}
			
			if( !isUpdate ) {
				$http.post( CHANNEL_URL, JSON.stringify($scope.channel) ).success( function(data) {
					$scope.alert.type = ( data.statusResult.value ) ? SUCCESS_MESSAGE : ERROR_MESSAGE;
					$scope.alert.message = data.message;
					
					if( data.statusResult.value ) {
						$scope.cancel();
						$scope.get();
					}
				});
			}
			else {
				$http.put( CHANNEL_URL + $scope.channel.id, JSON.stringify($scope.channel) ).success( function(data) {
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

		$scope.delete = function( channel ) {
			var response = confirm( DEFAULT_DELETE_MESSAGE );
			if( response ) {
				$http.delete( CHANNEL_URL + channel.id ).success( function(data) {
					if( data.statusResult.value ) {
						$scope.alert.type = ( data.statusResult.value ) ? SUCCESS_MESSAGE : ERROR_MESSAGE;
						$scope.alert.message = data.message;
						$scope.alert.show = true;
						$scope.get();
					}
				});
			}
		}
		
		$scope.update = function( channel ) {
			$scope.channel = ChannelDTO.build();
			$scope.channel.id = channel.id;
			$scope.channel.distinctive = channel.distinctive;
			$scope.channel.name = channel.name;
			$scope.channel.virtualChannel = channel.virtualChannel;
			$scope.channel.physicChannel = channel.physicChannel - 13;
			$scope.channel.quality = (channel.quality === CHANNEL_QUALITY.HD.VALUE) ? CHANNEL_QUALITY.HD.ID : CHANNEL_QUALITY.SD.ID;
			$scope.channel.resolution = (channel.resolution === CHANNEL_RESOLUTION.FULL.VALUE) ? CHANNEL_RESOLUTION.FULL.ID : CHANNEL_RESOLUTION.SD.ID;
			$scope.channel.power = channel.power;
			$scope.channel.acesli = channel.acesli;
			$scope.channel.longitude = channel.longitude;
			$scope.channel.latitude = channel.latitude;
			$scope.channel.effectiveDateStart = channel.effectiveDayStart;
			$scope.channel.effectiveDateEnd = channel.effectiveDayEnd;
			$scope.channel.channelBand = channel.channelBand;
			$scope.channel.population = channel.population;
			$scope.channel.concessionaire = channel.concessionaire;
			$scope.channel.concessionType = channel.concessionType;
			
			$scope.selectedChannelBand = channel.channelBand.id;
			$scope.selectedPopulation = channel.population.id;
			$scope.selectedConcessionaire = channel.concessionaire.id;
			$scope.selectedConcessionType = channel.concessionType.id;
			
			$scope.channel.active = channel.active;
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
	}]
);