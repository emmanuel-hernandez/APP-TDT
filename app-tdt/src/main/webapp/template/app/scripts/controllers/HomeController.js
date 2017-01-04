'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # HomeController
 * Controller of the sbAdminApp
 */
angular.module( APP_NAME ).controller( 'HomeController', ['$scope', '$http', 'httpService', function( $scope, $http, httpService ) {
	
	var init = function() {
		$scope.alert = AlertDTO.build( null, ERROR_MESSAGE, false );
		$scope.stats = new Array();		
		$scope.statsNumber = STATS_NUMBER;
		$scope.columns = { name: [ 'Distintivo', 'Nombre', 'Canal virtual', 'Canal físico', 'Calidad', 'Resolución', 'Banda'] };
		
		angular.element( '.panel-collapse' )
			.on( 'shown.bs.collapse', function() {
				angular.element( this ).parent().find( '.icon-collapse' ).removeClass( 'fa-plus' ).addClass( 'fa-minus' ); })
			.on( 'hidden.bs.collapse', function() {
				angular.element( this ).parent().find( '.icon-collapse' ).removeClass( 'fa-minus' ).addClass( 'fa-plus' ); });
			//.css('cursor', 'pointer');
		
		angular.element( '#collapseOne' ).on( 'hidden.bs.collapse', function() {
			resetChannelsByState();
		});
		angular.element( '#collapseTwo' ).on( 'hidden.bs.collapse', function() {
			resetChannelsByConcessionaire();
		})
		
		resetChannelsByState();
		resetChannelsByConcessionaire();
		initMap();
	}

	var resetChannelsByState = function() {
		$scope.selectedState = DEFAULT_SELECTED_VALUE;
		$scope.selectedPopulation = DEFAULT_SELECTED_VALUE;
		$scope.selectPopulation = false;
		$scope.channels = new Array();
	}
	
	var resetChannelsByConcessionaire = function() {
		$scope.selectedConcessionaire = DEFAULT_SELECTED_VALUE;
		$scope.selectedConcessionType = DEFAULT_SELECTED_VALUE;
		$scope.concessionaires = new Array();
		$scope.concessionTypes = new Array();
		$scope.channels = new Array();
	}
	
	var getStatesCount = function() {
		httpService.get( STATE_URL ).success( function(data) {
			if( data.statusResult.value ) {
				$scope.states = getCollectionSize( data.collection );
				$scope.stats.push( StatDTO.build( $scope.states, 'Estados', 'primary', 'bank', 'dashboard.state' ) );
			}
			else {
				$scope.alert.message = data.message;
			}
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}
		});
	};
	
	var getPopulationsCount = function() {
		httpService.get( POPULATION_URL ).success( function(data) {
			if( data.statusResult.value ) {
				$scope.populations = getCollectionSize( data.collection );
				$scope.stats.push( StatDTO.build( $scope.populations, 'Poblaciones', 'red', 'users', 'dashboard.population' ) );
			}
			else {
				$scope.alert.message = data.message;
			}
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}
		});
	};
	
	var getConcessionairesCount = function() {
		httpService.get( CONCESSIONAIRE_URL ).success( function(data) {
			if( data.statusResult.value ) {
				$scope.concessionaires = getCollectionSize( data.collection );
				$scope.stats.push( StatDTO.build( $scope.concessionaires, 'Concesionarias', 'yellow', 'wifi', 'dashboard.concessionaire' ) );
			}
			else {
				$scope.alert.message = data.message;
			}
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}
		});
	};

	var getChannelsCount = function() {
		httpService.get( CHANNEL_URL ).success( function(data) {
			if( data.statusResult.value ) {
				$scope.channels = getCollectionSize( data.collection );
				$scope.stats.push( StatDTO.build( $scope.channels, 'Canales', 'green', 'video-camera', 'dashboard.channel' ) );
			}
			else {
				$scope.alert.message = data.message;
			}
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}
		});
	};
	
	var getCollectionSize = function( collection ) {
		var size = 0;
		for( var i=0; i<collection.length; i++ ) {
			if( collection[i].active ) {
				size += 1;
			}
		}
		
		return size;
	}
	
	var getStates = function() {
		httpService.get( STATE_URL ).success( function(data) {
			if( data.statusResult.value ) {
				$scope.states = data.collection;
				$scope.selectPopulation = false;
			}
			$scope.alert.message = data.message;
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}			
		});
	};
	
	var getConcessionaires = function() {
		httpService.get( CONCESSIONAIRE_URL ).success( function(data) {
			if( data.statusResult.value ) {
				$scope.concessionaires = data.collection;
			}
			$scope.alert.message = data.message;
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}			
		});
	};
	
	var getConcessionTypes = function() {
		httpService.get( CONCESSION_TYPE_URL ).success( function(data) {
			if( data.statusResult.value ) {
				$scope.concessionTypes = data.collection;
			}
			$scope.alert.message = data.message;
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}			
		});
	};
	
	var getPopulationsByState = function( stateId ) {
		httpService.get( POPULATION_URL +'state/'+ stateId ).success( function(data) {
			if( data.statusResult.value ) {
				$scope.populations = data.collection;
				if( $scope.populations.length > 0 ) {
					$scope.selectedPopulation = DEFAULT_SELECTED_VALUE;
					$scope.selectPopulation = true;
				}
			}
			else {
				$scope.alert.message = data.message;
			}
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}			
		});
	};
	
	var getChannelsByPopulation = function( populationId ) {
		httpService.get( CHANNEL_URL +'population/'+ populationId ).success( function(data) {
			if( data.statusResult.value && data.collection.length > 0 ) {
				parseChannels( data.collection );
			}
			$scope.alert.message = data.message;
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}			
		});
	};

	var getChannelsByConcessionaire = function( concessionaireId ) {
		httpService.get( CHANNEL_URL +'concessionaire/'+ concessionaireId ).success( function(data) {
			if( data.statusResult.value && data.collection.length > 0 ) {
				parseChannels( data.collection );
			}
			$scope.alert.message = data.message;
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}			
		});
	}
	
	var getChannelsByConcessionType = function( concessionTypeId ) {
		httpService.get( CHANNEL_URL +'concessionType/'+ concessionTypeId ).success( function(data) {
			if( data.statusResult.value && data.collection.length > 0 ) {
				parseChannels( data.collection );
			}
			$scope.alert.message = data.message;
		}).finally( function() {
			if( $scope.alert.message.length > 0 ) {
				$scope.alert.show = true;
			}			
		});
	}
	
	var parseChannels = function( collection ) {
		$scope.channels = new Array();

		for(var i=0; i<collection.length; i++ ) {
			var channel = collection[ i ];
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
	
	var parseLatLong = function( l ) {
		console.log( 'Parsing. ' + l );
		
		var ln = '' + l;
		var out = '';
		var char = '';
		var decimalPosition = l.lastIndexOf( '-' ) >= 0 ? l.length > 7 ? 4 : 3 : 2;
		
		for( var i=0; i<ln.length; i++ ) {
			char = ln.substr( i, 1 );
			
			if( char == '.' ) {
				continue;
			}
			
			out += ( i == decimalPosition ) ? '.' + char : char;
		}
		
		return out;
	}
	
	var map;
	var initMap = function() {
		console.log( 'Initing...' );
		map = new google.maps.Map(document.getElementById( 'map' ), {
			center: {
				lat: DEFAULT_LATITUDE,
				lng: DEFAULT_LONGITUDE
			},
			zoom: 9,
			zoomControl: false,
		    scaleControl: true
		});
	}
	
	var updateMap = function( latitude, longitude ) {
		map.setCenter({
			lat: parseFloat( latitude ),
			lng: parseFloat( longitude )
		});
	} 
	
	$scope.details = function( channel ) {
		$scope.selectedChannel = ChannelDTO.build();
		$scope.selectedChannel.id = channel.id;
		$scope.selectedChannel.distinctive = channel.distinctive;
		$scope.selectedChannel.name = channel.name;
		$scope.selectedChannel.virtualChannel = channel.virtualChannel;
		$scope.selectedChannel.physicChannel = channel.physicChannel - 13;
		$scope.selectedChannel.quality = (channel.quality === CHANNEL_QUALITY.HD.VALUE) ? CHANNEL_QUALITY.HD.ID : CHANNEL_QUALITY.SD.ID;
		$scope.selectedChannel.resolution = (channel.resolution === CHANNEL_RESOLUTION.FULL.VALUE) ? CHANNEL_RESOLUTION.FULL.ID : CHANNEL_RESOLUTION.SD.ID;
		$scope.selectedChannel.power = channel.power;
		$scope.selectedChannel.acesli = channel.acesli;
		$scope.selectedChannel.latitude = channel.latitude;
		$scope.selectedChannel.longitude = '-' + channel.longitude;
		$scope.selectedChannel.effectiveDateStart = channel.effectiveDateStart;
		$scope.selectedChannel.effectiveDateEnd = channel.effectiveDateEnd;
		$scope.selectedChannel.channelBand = channel.channelBand;
		$scope.selectedChannel.population = channel.population;
		$scope.selectedChannel.concessionaire = channel.concessionaire;
		$scope.selectedChannel.concessionType = channel.concessionType;
		
		updateMap( $scope.selectedChannel.latitude, $scope.selectedChannel.longitude );
		angular.element( "#detailModal" ).modal({show:'true'});
	}
	
	$scope.stateOnChange = function() {
		if( $scope.selectedState > 0 ) {
			getPopulationsByState( $scope.selectedState );
		}
	};
	
	$scope.populationOnChange = function() {
		if( $scope.selectedPopulation > 0 ) {
			getChannelsByPopulation( $scope.selectedPopulation );
		}
	};
	
	$scope.concessionaireOnChange = function() {		
		if( $scope.selectedConcessionaire > 0 ) {
			$scope.selectedConcessionType = DEFAULT_SELECTED_VALUE;
			getChannelsByConcessionaire( $scope.selectedConcessionaire );
		}
	};
	
	$scope.concessionTypeOnChange = function() {
		if( $scope.selectedConcessionType > 0 ) {
			$scope.selectedConcessionaire = DEFAULT_SELECTED_VALUE;
			getChannelsByConcessionType( $scope.selectedConcessionType );
		}
	};

	init();
	/*
	getStatesCount();
	getPopulationsCount();
	getConcessionairesCount();
	getChannelsCount();
	 */	

	getStates();
	getConcessionaires();	
	getConcessionTypes();	
}]);
