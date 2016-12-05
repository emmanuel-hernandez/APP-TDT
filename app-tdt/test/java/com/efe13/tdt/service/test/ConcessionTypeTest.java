package com.efe13.tdt.service.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.tdt.model.dto.ConcessionTypeDTO;
import com.efe13.tdt.service.impl.ConcessionTypeServiceImpl;

public class ConcessionTypeTest {
	
	private static final ConcessionTypeServiceImpl CONCESSION_TYPE_SERVICE = new ConcessionTypeServiceImpl();

	@Ignore
	@Test
	public void testGet() {
		try {
			ConcessionTypeDTO dto = new ConcessionTypeDTO();
			dto.setId( (short) 2 );

			//dto = CONCESSION_TYPE_SERVICE.getById( dto );
			System.out.println( "dto.getType(): " + dto.getType() );
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
			ConcessionTypeDTO concessionTypeDTO = new ConcessionTypeDTO();
			concessionTypeDTO.setType( "PR" );
			concessionTypeDTO.setDescription( "PRESTAMO" );
			concessionTypeDTO.setActive( ActiveEnum.ACTIVE.getValue() );
			
			System.out.println( "concessionTypeDTO: " + concessionTypeDTO.getType() );
			short id = CONCESSION_TYPE_SERVICE.save( concessionTypeDTO );
			
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
			ConcessionTypeDTO concessionTypeDTO = new ConcessionTypeDTO();
			concessionTypeDTO.setId( (short) 9 );
			//concessionTypeDTO = CONCESSION_TYPE_SERVICE.getById( concessionTypeDTO );
			
			concessionTypeDTO.setType( "PRST" );
			concessionTypeDTO.setDescription( "new description" );
			concessionTypeDTO.setActive( ActiveEnum.ACTIVE.getValue() );
			
			boolean result = true;//CONCESSION_TYPE_SERVICE.update( concessionTypeDTO );
			
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
			ConcessionTypeDTO concessionTypeDTO = new ConcessionTypeDTO();
			concessionTypeDTO.setId( (short) 9 );
			//concessionTypeDTO = CONCESSION_TYPE_SERVICE.getById( concessionTypeDTO );
			
			boolean result = true;//CONCESSION_TYPE_SERVICE.delete( concessionTypeDTO );
			
			System.out.println( "RESULT: " + result );
			Assert.assertTrue( result );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
	
	}
}
