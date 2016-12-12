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
const POPULATION_URL     = REST_URL + '/population/';
const CONCESSIONAIRE_URL = REST_URL + '/concessionaire/';
const CONCESSION_TYPE_URL= REST_URL + '/concessionType/';


