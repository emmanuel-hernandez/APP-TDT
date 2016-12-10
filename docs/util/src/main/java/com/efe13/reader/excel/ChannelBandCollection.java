package com.efe13.reader.excel;

import java.util.ArrayList;
import java.util.List;

public class ChannelBandCollection {

	private List<String> list = new ArrayList<String>();
	
	public ChannelBandCollection() {
		list.add( "TV" );
		list.add( "TDT" );
	}
	
	public int getChannelBandPk( String name ) {
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
