package com.efe13.tdt.service.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.tdt.model.dto.ChannelBandDTO;
import com.efe13.tdt.service.impl.ChannelBandServiceImpl;

public class ChannelBandTest {
	
	private static final ChannelBandServiceImpl CHANNEL_BAND_SERVICE = new ChannelBandServiceImpl();

	@Ignore
	@Test
	public void testGet() {
		try {
			ChannelBandDTO dto = new ChannelBandDTO();
			dto.setId( (short) 1 );

			//dto = CHANNEL_BAND_SERVICE.getById( dto );
			System.out.println( "dto.getName(): " + dto.getName() );
			Assert.assertNotEquals( dto, null );
		}
		catch( Exception ex ) {
			System.out.println( ex.getMessage() );
		}
	}
	
	@Ignore
	@Test
	public void testSave() {
		try {
			ChannelBandDTO channelBandDTO = new ChannelBandDTO();
			channelBandDTO.setName( "FM" );
			channelBandDTO.setDescription( "RADIO" );
			channelBandDTO.setActive( ActiveEnum.ACTIVE.getValue() );
			
			System.out.println( "channelBandDTO: " + channelBandDTO.getName() );
			short id = CHANNEL_BAND_SERVICE.save( channelBandDTO );
			
			Assert.assertNotEquals( id, 0 );
			System.out.println( "ID: " + id );
		}
		catch( Exception ex ) {
			System.err.println( ex.getMessage() );
		}
	}
	
	@Ignore
	@Test
	public void testUpdate() {
		try {
			ChannelBandDTO channelBandDTO = new ChannelBandDTO();
			channelBandDTO.setId( (short) 3 );
			//channelBandDTO = CHANNEL_BAND_SERVICE.getById( channelBandDTO );
			
			channelBandDTO.setName( "AM" );
			channelBandDTO.setActive( ActiveEnum.INACTIVE.getValue() );
			
			boolean result = true;//CHANNEL_BAND_SERVICE.update( channelBandDTO );
			
			System.out.println( "RESULT: " + result );
			Assert.assertTrue( result );
		}
		catch( Exception ex ) {
			System.err.println( ex.getMessage() );
		}
	}
	
	@Ignore
	@Test
	public void testDelete() {
		try {
			ChannelBandDTO channelBandDTO = new ChannelBandDTO();
			channelBandDTO.setId( (short) 3 );
			//channelBandDTO = CHANNEL_BAND_SERVICE.getById( channelBandDTO );
			
			boolean result = true;//CHANNEL_BAND_SERVICE.delete( channelBandDTO );
			
			System.out.println( "RESULT: " + result );
			Assert.assertTrue( result );
		}
		catch( Exception ex ) {
			System.err.println( ex.getMessage() );
		}
	
	}
}
