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
const GET_PARAMETER_NAME = 'queryHelper';
const ORDER_ASCENDING = true;
const ORDER_DESCENDING = true;
const ERROR_MESSAGE = 'danger';
const WARNING_MESSAGE = 'warning';
const SUCCESS_MESSAGE = 'success';
const DEFAULT_SELECTED_VALUE = -1;
const DEFAULT_DELETE_MESSAGE = '¿Estas seguro que deseas eliminar este registro?';

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