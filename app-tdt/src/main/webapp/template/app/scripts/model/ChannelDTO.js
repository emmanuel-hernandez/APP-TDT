/**
 * Object representation for a Channel element
 */

var ChannelDTO = {
		build: function() {
			return {
				id: 0,
				distinctive: null,
				name: null,
				virtualChannel: 0,
				physicChannel: 14,
				quality: 1,
				resolution: 1,
				power: 0,
				acesli: 0,
				longitude: 0,
				latitude: 0,
				effectiveDateStart: null,
				effectiveDateEnd: null,
				channelBand: 1,
				population: -1,
				concessionaire: -1,
				concessionType: -1
			}
		}
}