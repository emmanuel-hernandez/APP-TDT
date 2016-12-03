package com.efe13.tdt.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusResultService {
	
	STATUS_SUCCESS( true ),
	STATUS_FAILED( false );

	private boolean statusResult;
	
	private StatusResultService( boolean statusResult ) {
		this.statusResult = statusResult;
	}
	
	public boolean getValue() {
		return statusResult;
	}
}
