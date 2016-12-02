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
			dto.setId( (short) 20 );
			
			dto = POPULATION_SERVICE.getById( dto );
			System.out.println( "dto.getName(): " + dto.getName() );
			Assert.assertNotEquals( dto, null );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void testSave() {
		try {
			PopulationDTO populationDTO = new PopulationDTO();
			//populationDTO.setName( "POBLACION" );
			populationDTO.setActive( ActiveEnum.ACTIVE.getValue() );
			
			StateDTO stateDTO = new StateDTO();
			stateDTO.setId( (short) 12 );
			stateDTO = STATE_SERVICE.getById( stateDTO );
			
			populationDTO.setState( stateDTO );
			System.out.println( "Assign State: " + stateDTO.getName() + " to population: " + populationDTO.getName() );
			short id = POPULATION_SERVICE.save( populationDTO );
			
			System.out.println( "ID: " + id );
			Assert.assertNotEquals( id, 0 );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void testUpdate() {
		try {
			PopulationDTO populationDTO = new PopulationDTO();
			populationDTO.setId( (short) 327 );
			populationDTO = (PopulationDTO) POPULATION_SERVICE.getById( populationDTO );
	
			StateDTO stateDTO = new StateDTO();
			stateDTO.setId( (short) 16 );
			stateDTO = STATE_SERVICE.getById( stateDTO );
			
			populationDTO.setName( "NEW NAME POPULATION" );
			populationDTO.setState( stateDTO );
			populationDTO.setActive( ActiveEnum.INACTIVE.getValue() );
			
			boolean result = POPULATION_SERVICE.update( populationDTO );
			
			System.out.println( "RESULT: " + result );
			Assert.assertTrue( result );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void testDelete() {
		try {
			PopulationDTO populationDTO = new PopulationDTO();
			populationDTO.setId( (short) 328 );
			populationDTO = (PopulationDTO) POPULATION_SERVICE.getById( populationDTO );
			
			boolean result = POPULATION_SERVICE.delete( populationDTO );
			
			System.out.println( "RESULT: " + result );
			Assert.assertTrue( result );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
	
	}
}
