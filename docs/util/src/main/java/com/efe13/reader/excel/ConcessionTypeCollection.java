package com.efe13.reader.excel;

import java.util.ArrayList;
import java.util.List;

public class ConcessionTypeCollection {

	private List<String> list = new ArrayList<String>();
	
	public ConcessionTypeCollection() {
		list.add( "(C)" );
		list.add( "(P)" );
		list.add( "(CP)" );
		list.add( "(C)*" );
		list.add( "(P)*" );
		list.add( "(CP)*" );
		list.add( "(CS)*" );
		list.add( "(P)**" );
		list.add( "(CP)**" );
		list.add( "(CS)**" );
	}
	
	public int getConcessionTypePk( String name ) {
		int pk = 1;
		for( String element : list ) {
			if( element.compareToIgnoreCase( name ) == 0 ) {
				return pk;
			}
			pk += 1;
		}
		return 0;
	}
}
