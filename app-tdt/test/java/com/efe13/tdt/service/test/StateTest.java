package com.efe13.tdt.service.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.efe13.tdt.model.dto.StateDTO;
import com.efe13.tdt.service.impl.StateServiceImpl;

public class StateTest {

	private static final StateServiceImpl STATE_SERVICE = new StateServiceImpl();
	
	@Test
	public void testGetState() {
		try {
			StateDTO dto = new StateDTO();
			dto.setId( (short) 20 );
			
			dto = STATE_SERVICE.getById( dto );
			System.out.println( "dto.getName(): " + dto.getName() );
			Assert.assertNotEquals( dto, null );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void testSaveState() {
		StateDTO stateDTO = new StateDTO();
		stateDTO.setName( "ESTADO" );
		stateDTO.setShortName( "EST" );
		
		short id = (short) STATE_SERVICE.save( stateDTO );
		
		System.out.println( "ID: " + id ); //37
		Assert.assertNotEquals( id, 0 );
	}
	
	@Ignore
	@Test
	public void testUpdateState() {
		StateDTO stateDTO = new StateDTO();
		stateDTO.setId( (short) 37 );
		stateDTO = (StateDTO) STATE_SERVICE.getById( stateDTO );

		stateDTO.setName( "NEW NAME STATE" );
		stateDTO.setShortName( "NNSTA" );
		stateDTO.setActive( false );
		
		boolean result = STATE_SERVICE.update( stateDTO );
		
		System.out.println( "RESULT: " + result );
		Assert.assertTrue( result );
	}
	
	@Ignore
	@Test
	public void testDeleteState() {
		StateDTO stateDTO = new StateDTO();
		stateDTO.setId( (short) 37 );
		stateDTO = (StateDTO) STATE_SERVICE.getById( stateDTO );
		
		boolean result = STATE_SERVICE.delete( stateDTO );
		
		System.out.println( "RESULT: " + result );
		Assert.assertTrue( result );
	
	}
}