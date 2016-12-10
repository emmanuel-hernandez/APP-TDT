package com.efe13.reader.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import javax.swing.JFileChooser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reader {

	private static final int COLUMN_POPULATION = 1;
	private static final int COLUMN_CONCESSION_TYPE = 3;
	private static final int COLUMN_CONCESSIONAIRE = 4;
	private static final int COLUMN_DISTINCTIVE = 5;
	private static final int COLUMN_CHANNEL_BAND = 6;
	private static final int COLUMN_PHYSIC_CHANNEL = 7;
	private static final int COLUMN_POWER = 8;
	private static final int COLUMN_LATITUDE = 9;
	private static final int COLUMN_LONGITUDE = 10;
	private static final int COLUMN_ACESLI = 11;
	private static final int COLUMN_EFFECTIVE_DAY_START = 12;
	private static final int COLUMN_EFFECTIVE_DAY_END = 13;
	
	
	public Reader() {
		try {
			/*JFileChooser jFileChooser = new JFileChooser(); 
			int action = jFileChooser.showOpenDialog( null );
			if( action == JFileChooser.OPEN_DIALOG ) {*/
	            FileInputStream file = new FileInputStream( new File( "C:/00_Emmanuel/LABS/JAVA/APP_TDT/APP-TDT/docs/infraestructurasradiodifusiontelevision31-03-16.xlsx" ) );
	            //System.out.println( jFileChooser.getSelectedFile().toString() );
	 
	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(file);

	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(0);
	 
	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                //For each row, iterate through all the columns
	                Iterator<Cell> cellIterator = row.cellIterator();
	                while (cellIterator.hasNext()) {
	                    Cell cell = cellIterator.next();
	                    
	                    if( cell.getRowIndex() < 5 ) {
	                    	continue;
	                    }
	                    if( cell.getRowIndex() > 825 ) {
	                    	break;
	                    }
	                    
	                    boolean isNumericValue = cell.getCellType() == Cell.CELL_TYPE_NUMERIC ? true : false;
	                    switch( cell.getColumnIndex() ) {
							case COLUMN_POPULATION:
								if( isNumericValue ) {
									;
								}
								else {
									;
								}
								break;
							case COLUMN_CONCESSION_TYPE:
								if( isNumericValue ) {
									;
								}
								else {
									;
								}
								break;
							case COLUMN_CONCESSIONAIRE:
								if( isNumericValue ) {
									;
								}
								else {
									;
								}
								break;
							case COLUMN_DISTINCTIVE:
								if( isNumericValue ) {
									;
								}
								else {
									;
								}
								break;
							case COLUMN_CHANNEL_BAND:
								if( isNumericValue ) {
									;
								}
								else {
									;
								}
								break;
							case COLUMN_PHYSIC_CHANNEL:
								if( isNumericValue ) {
									;
								}
								else {
									;
								}
								break;
							case COLUMN_POWER:
								if( isNumericValue ) {
									;
								}
								else {
									;
								}
								break;
							case COLUMN_LATITUDE:
								if( isNumericValue ) {
									;
								}
								else {
									;
								}
								break;
							case COLUMN_LONGITUDE:
								if( isNumericValue ) {
									;
								}
								else {
									;
								}
								break;
							case COLUMN_ACESLI:
								if( isNumericValue ) {
									;
								}
								else {
									;
								}
								break;
							case COLUMN_EFFECTIVE_DAY_START:
								if( isNumericValue ) {
									;
								}
								else {
									;
								}
								break;
							case COLUMN_EFFECTIVE_DAY_END:
								if( isNumericValue ) {
									;
								}
								else {
									;
								}
								break;

	                    }
	                }
	                //System.out.println("");
	            }
	            workbook.close();
	            file.close();
			//}
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}
