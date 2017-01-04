/**
 * 
 */

const APP_NAME = 'sbAdminApp';

/* Connection */
const PROTOCOL = 'http';
const SERVER   = 'localhost';
const PORT     = '8181';
const REST_URL = PROTOCOL + "://" + SERVER + ':' + PORT;

/* Routes */
const CHANNEL_URL 		 = REST_URL + '/channel/';
const CHANNEL_BAND_URL 	 = REST_URL + '/channelBand/';
const STATE_URL			 = REST_URL + '/state/';
const POPULATION_URL     = REST_URL + '/population/';
const CONCESSIONAIRE_URL = REST_URL + '/concessionaire/';
const CONCESSION_TYPE_URL= REST_URL + '/concessionType/';

/* Global Config */
const GMAP_API_KEY = 'AIzaSyCL6KrEpBzBrPSeXMEUMlxhcbdYS8mL_7k';
const DEFAULT_LATITUDE = 19.3910038;
const DEFAULT_LONGITUDE = -99.2837001;

const GET_PARAMETER_NAME = 'queryHelper';
const ORDER_ASCENDING = true;
const ORDER_DESCENDING = true;
const ERROR_MESSAGE = 'danger';
const WARNING_MESSAGE = 'warning';
const SUCCESS_MESSAGE = 'success';
const DEFAULT_SELECTED_VALUE = -1;
const DEFAULT_DELETE_MESSAGE = '¿Estas seguro que deseas eliminar este registro?';
const ACESLI_TOOLTIP = 'Altura del Centro Eléctrico Sobre el Lugar de Instalación';
const LEFT_HAND_SIDE_PAGES = 3;
const RIGHT_HAND_SIDE_PAGES = 3;
const KEY_CODE = {
	INTRO_KEY: 13
};

/* Utilities Constants */

//Home
const STATS_NUMBER = 4;

//Channels
const FIRST_PHYSIC_CHANNEL = 14;
const LAST_PHYSIC_CHANNEL = 84;
const CHANNEL_QUALITY = {
	SD: {
		ID: 1,
		VALUE: 'SD'
	},
	HD: {
		ID: 2,
		VALUE: 'HD'
	}
};
const CHANNEL_RESOLUTION = {
	SD: {
		ID: 1,
		VALUE: '480i'
	},
	FULL: {
		ID: 2,
		VALUE: '1080i'
	}
};