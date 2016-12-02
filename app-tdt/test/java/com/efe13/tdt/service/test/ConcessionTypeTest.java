package com.efe13.tdt.service.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.tdt.model.dto.ConcessionTypeDTO;
import com.efe13.tdt.service.impl.ConcessionTypeServiceImpl;

public class ConcessionTypeTest {
	
	private static final ConcessionTypeServiceImpl CONCESSION_TYPE_SERVICE = new ConcessionTypeServiceImpl();

	@Test
	public void testGet() {
		try {
			ConcessionTypeDTO dto = new ConcessionTypeDTO();
			dto.setId( (short) 20 );
			
			dto = CONCESSION_TYPE_SERVICE.getById( dto );
			System.out.println( "dto.getType(): " + dto.getType() );
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
			ConcessionTypeDTO concessionTypeDTO = new ConcessionTypeDTO();
			//concessionTypeDTO.setName( "POBLACION" );
			concessionTypeDTO.setActive( ActiveEnum.ACTIVE.getValue() );
			
			System.out.println( "concessionTypeDTO: " + concessionTypeDTO.getType() );
			short id = CONCESSION_TYPE_SERVICE.save( concessionTypeDTO );
			
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
			ConcessionTypeDTO concessionTypeDTO = new ConcessionTypeDTO();
			concessionTypeDTO.setId( (short) 327 );
			concessionTypeDTO = (ConcessionTypeDTO) CONCESSION_TYPE_SERVICE.getById( concessionTypeDTO );
			
			concessionTypeDTO.setType( "NEW NAME POPULATION" );
			concessionTypeDTO.setActive( ActiveEnum.INACTIVE.getValue() );
			
			boolean result = CONCESSION_TYPE_SERVICE.update( concessionTypeDTO );
			
			System.out.println( "RESULT: " + result );
			Assert.assertTrue( result );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void testDelete() {
		try {
			ConcessionTypeDTO concessionTypeDTO = new ConcessionTypeDTO();
			concessionTypeDTO.setId( (short) 328 );
			concessionTypeDTO = (ConcessionTypeDTO) CONCESSION_TYPE_SERVICE.getById( concessionTypeDTO );
			
			boolean result = CONCESSION_TYPE_SERVICE.delete( concessionTypeDTO );
			
			System.out.println( "RESULT: " + result );
			Assert.assertTrue( result );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
	
	}
}
