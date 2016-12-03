package com.efe13.tdt.service.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.tdt.model.dto.ChannelBandDTO;
import com.efe13.tdt.model.dto.ChannelDTO;
import com.efe13.tdt.model.dto.ConcessionTypeDTO;
import com.efe13.tdt.model.dto.ConcessionaireDTO;
import com.efe13.tdt.model.dto.PopulationDTO;
import com.efe13.tdt.service.impl.ChannelServiceImpl;

public class ChannelTest {
	
	private static final ChannelServiceImpl CHANNEL_SERVICE = new ChannelServiceImpl();

	@Ignore
	@Test
	public void testGet() {
		try {
			ChannelDTO dto = new ChannelDTO();
			dto.setId( (short) 2 );

			dto = CHANNEL_SERVICE.getById( dto );
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
			ChannelDTO channel = new ChannelDTO();
			channel.setDistinctive( "XHDD" );
			channel.setName( "DISCOVERY DD" );
			channel.setVirtualChannel( 3.1F );
			channel.setPhysicChannel( (short)40 );
			channel.setQuality( "HD" );
			channel.setResolution( "1080i" );
			channel.setPower( (short) 80 );
			channel.setAcesli( (short) 30 );
			channel.setLongitude( "18.1515" );
			channel.setLatitude( "22.0451" );
			channel.setEffectiveDateStart( "2016-08-15" );
			channel.setEffectiveDateEnd( "2036-08-15" );
			
			ChannelBandDTO channelBandDTO = new ChannelBandDTO();
			channelBandDTO.setId( (short) 1 );
			channel.setChannelBand( channelBandDTO );
			
			PopulationDTO populationDTO = new PopulationDTO();
			populationDTO.setId( (short) 22 );
			channel.setPopulation( populationDTO );
			
			ConcessionaireDTO concessionaireDTO = new ConcessionaireDTO();
			concessionaireDTO.setId( (short) 11 );
			channel.setConcessionaire( concessionaireDTO );
			
			ConcessionTypeDTO concessionTypeDTO = new ConcessionTypeDTO();
			concessionTypeDTO.setId( (short) 3 );
			channel.setConcessionType( concessionTypeDTO );
			channel.setActive( ActiveEnum.ACTIVE.getValue() );
			
			System.out.println( "channelDTO: " + channel.getName() );
			short id = CHANNEL_SERVICE.save( channel );
			
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
			ChannelDTO channel = new ChannelDTO();
			channel.setId( (short) 4 );
			channel.setDistinctive( "XHDDDD" );
			channel.setName( "DISCOVERY DDDD" );
			channel.setVirtualChannel( 5.1F );
			channel.setPhysicChannel( (short)60 );
			channel.setQuality( "SD" );
			channel.setResolution( "480i" );
			channel.setPower( (short) 30 );
			channel.setAcesli( (short) 60 );
			channel.setLongitude( "20.1515" );
			channel.setLatitude( "12.0451" );
			channel.setEffectiveDateStart( "2016-11-15" );
			channel.setEffectiveDateEnd( "2036-11-15" );
			
			ChannelBandDTO channelBandDTO = new ChannelBandDTO();
			channelBandDTO.setId( (short) 2 );
			channel.setChannelBand( channelBandDTO );
			
			PopulationDTO populationDTO = new PopulationDTO();
			populationDTO.setId( (short) 18 );
			channel.setPopulation( populationDTO );
			
			ConcessionaireDTO concessionaireDTO = new ConcessionaireDTO();
			concessionaireDTO.setId( (short) 5 );
			channel.setConcessionaire( concessionaireDTO );
			
			ConcessionTypeDTO concessionTypeDTO = new ConcessionTypeDTO();
			concessionTypeDTO.setId( (short) 1 );
			channel.setConcessionType( concessionTypeDTO );
			channel.setActive( ActiveEnum.INACTIVE.getValue() );
			
			boolean result = CHANNEL_SERVICE.update( channel );
			
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
			ChannelDTO channelDTO = new ChannelDTO();
			channelDTO.setId( (short) 4 );
			channelDTO = CHANNEL_SERVICE.getById( channelDTO );
			
			boolean result = CHANNEL_SERVICE.delete( channelDTO );
			
			System.out.println( "RESULT: " + result );
			Assert.assertTrue( result );
		}
		catch( Exception ex ) {
			System.err.println( ex.getMessage() );
		}
	
	}
}
