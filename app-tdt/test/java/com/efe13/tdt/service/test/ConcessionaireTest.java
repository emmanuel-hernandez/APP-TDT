package com.efe13.tdt.service.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.tdt.model.dto.ConcessionaireDTO;
import com.efe13.tdt.service.impl.ConcessionaireServiceImpl;

public class ConcessionaireTest {
	
	private static final ConcessionaireServiceImpl CONCESSIONAIRE_SERVICE = new ConcessionaireServiceImpl();

	@Ignore
	@Test
	public void testGet() {
		try {
			ConcessionaireDTO dto = new ConcessionaireDTO();
			dto.setId( (short) 222 );

			dto = CONCESSIONAIRE_SERVICE.getById( dto );
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
			ConcessionaireDTO concessionTypeDTO = new ConcessionaireDTO();
			concessionTypeDTO.setName( "NUEVA TELEVISORA DEL GOLFO MX" );
			concessionTypeDTO.setActive( ActiveEnum.ACTIVE.getValue() );
			
			System.out.println( "concessionTypeDTO: " + concessionTypeDTO.getName() );
			short id = CONCESSIONAIRE_SERVICE.save( concessionTypeDTO );
			
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
			ConcessionaireDTO concessionTypeDTO = new ConcessionaireDTO();
			concessionTypeDTO.setId( (short) 108 );
			concessionTypeDTO = CONCESSIONAIRE_SERVICE.getById( concessionTypeDTO );
			
			concessionTypeDTO.setName( "NUEVA TELEVISORA DEL PACIFICO" );
			concessionTypeDTO.setActive( ActiveEnum.INACTIVE.getValue() );
			
			boolean result = CONCESSIONAIRE_SERVICE.update( concessionTypeDTO );
			
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
			ConcessionaireDTO concessionTypeDTO = new ConcessionaireDTO();
			concessionTypeDTO.setId( (short) 108 );
			concessionTypeDTO = CONCESSIONAIRE_SERVICE.getById( concessionTypeDTO );
			
			boolean result = CONCESSIONAIRE_SERVICE.delete( concessionTypeDTO );
			
			System.out.println( "RESULT: " + result );
			Assert.assertTrue( result );
		}
		catch( Exception ex ) {
			System.err.println( ex.getMessage() );
		}
	
	}
}
