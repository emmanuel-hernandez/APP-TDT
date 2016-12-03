package com.efe13.tdt.service.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.tdt.model.dto.PopulationDTO;
import com.efe13.tdt.model.dto.StateDTO;
import com.efe13.tdt.service.impl.PopulationServiceImpl;
import com.efe13.tdt.service.impl.StateServiceImpl;

public class PopulationTest {
	
	private static final PopulationServiceImpl POPULATION_SERVICE = new PopulationServiceImpl();
	private static final StateServiceImpl STATE_SERVICE = new StateServiceImpl();

	@Ignore
	@Test
	public void testGet() {
		try {
			PopulationDTO dto = new PopulationDTO();
			dto.setId( (short) 360 );

			dto = POPULATION_SERVICE.getById( dto );
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
			PopulationDTO populationDTO = new PopulationDTO();
			populationDTO.setName( "POPULATION" );
			populationDTO.setActive( ActiveEnum.ACTIVE.getValue() );
			
			StateDTO state = new StateDTO();
			state.setId( (short) 20 );
			state =  STATE_SERVICE.getById( state );
			
			populationDTO.setState( state );
			
			System.out.println( "populationDTO: " + populationDTO.getName() );
			short id = POPULATION_SERVICE.save( populationDTO );
			
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
			PopulationDTO populationDTO = new PopulationDTO();
			populationDTO.setId( (short) 329 );
			populationDTO = POPULATION_SERVICE.getById( populationDTO );
			
			populationDTO.setName( "NEW POPULATION" );
			populationDTO.setActive( ActiveEnum.INACTIVE.getValue() );
			
			StateDTO state = new StateDTO();
			state.setId( (short) 12 );
			state =  STATE_SERVICE.getById( state );
			
			boolean result = POPULATION_SERVICE.update( populationDTO );
			
			System.out.println( "RESULT: " + result );
			Assert.assertTrue( result );
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
			PopulationDTO populationDTO = new PopulationDTO();
			populationDTO.setId( (short) 329 );
			populationDTO = POPULATION_SERVICE.getById( populationDTO );
			
			boolean result = POPULATION_SERVICE.delete( populationDTO );
			
			System.out.println( "RESULT: " + result );
			Assert.assertTrue( result );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
	
	}
}
