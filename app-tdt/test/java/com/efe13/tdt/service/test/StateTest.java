package com.efe13.tdt.service.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.tdt.model.dto.StateDTO;
import com.efe13.tdt.service.impl.StateServiceImpl;

public class StateTest {
	
	private static final StateServiceImpl STATE_SERVICE = new StateServiceImpl();

	@Ignore
	@Test
	public void testGet() {
		try {
			StateDTO dto = new StateDTO();
			dto.setId( (short) 20 );

			//dto = STATE_SERVICE.getById( dto );
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
			StateDTO stateDTO = new StateDTO();
			stateDTO.setName( "STATE" );
			stateDTO.setShortName( "ST" );
			stateDTO.setActive( ActiveEnum.ACTIVE.getValue() );
			
			System.out.println( "stateDTO: " + stateDTO.getName() );
			short id = STATE_SERVICE.save( stateDTO );
			
			Assert.assertNotEquals( id, 0 );
			System.out.println( "ID: " + id );
		}
		catch( Exception ex ) {
			System.err.println( ex.getMessage() );
			ex.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void testUpdate() {
		try {
			StateDTO stateDTO = new StateDTO();
			stateDTO.setId( (short) 38 );
			//stateDTO = STATE_SERVICE.getById( stateDTO );
			
			stateDTO.setName( "NEW STATE" );
			stateDTO.setShortName( "NST" );
			stateDTO.setActive( ActiveEnum.INACTIVE.getValue() );
			
			//boolean result = STATE_SERVICE.update( stateDTO );
			
			//System.out.println( "RESULT: " + result );
			//Assert.assertTrue( result );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
			System.err.println( ex.getMessage() );
		}
	}
	
	@Ignore
	@Test
	public void testDelete() {
		try {
			StateDTO stateDTO = new StateDTO();
			stateDTO.setId( (short) 38 );
			//stateDTO = STATE_SERVICE.getById( stateDTO );
			
			//boolean result = STATE_SERVICE.delete( stateDTO );
			
			//System.out.println( "RESULT: " + result );
			//Assert.assertTrue( result );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
	
	}
}
