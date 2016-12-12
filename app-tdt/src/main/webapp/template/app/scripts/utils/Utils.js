/**
 * Utilities methods for the app
 */

function getInstance( object ) {
	return JSON.parse( JSON.stringify( object ) );
}