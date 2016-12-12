package com.efe13.reader.excel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	
	private String default_file_name;
	private static String locationFile = "/Users/Emmanuel/Documents/LABS/JAVA/APP-TDT/APP-TDT/docs/";
	
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	
	public Writer() throws IOException {
		this( "export.txt" );
	}
	
	public Writer( String fileName ) throws IOException {
		default_file_name = fileName;
		
		File file = new File( locationFile + default_file_name );
		if( !file.exists() ) {
			file.createNewFile();
		}
		
		fileWriter = new FileWriter( file );
		bufferedWriter = new BufferedWriter( fileWriter );
	}
	
	public void write( String content ) throws IOException {
		bufferedWriter.write( content );
		
		bufferedWriter.flush();
		bufferedWriter.close();
	}
}