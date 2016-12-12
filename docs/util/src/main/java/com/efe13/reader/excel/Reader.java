package com.efe13.reader.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reader {

	private static final int COLUMN_NUMBER = 0;
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
	
	private static final int INITIAL_ROW = 6;
	private static final int FINAL_ROW = 826;
	
	private static final String FILE_NAME = "channel_insert.sql";
	private final PopulationsCollection populationsCollection = new PopulationsCollection();
	private final ConcessionTypeCollection concessionTypeCollection = new ConcessionTypeCollection();
	private final ConcessionaireCollection concessionaireCollection = new ConcessionaireCollection();
	private final ChannelBandCollection channelBandCollection = new ChannelBandCollection();
	
	public Reader() {
		try {
			// C:/00_Emmanuel/LABS/JAVA/APP_TDT/APP-TDT/docs/infraestructurasradiodifusiontelevision31-03-16.xlsx
            FileInputStream file = new FileInputStream( new File( "/Users/Emmanuel/Documents/LABS/JAVA/APP-TDT/APP-TDT/docs/infraestructurasradiodifusiontelevision31-03-16.xlsx" ) );
            XSSFWorkbook workbook = new XSSFWorkbook(file); //Create Workbook instance holding reference to .xlsx file
            XSSFSheet sheet = workbook.getSheetAt(0); //Get first/desired sheet from the workbook
            Iterator<Row> rowIterator = sheet.iterator(); //Iterate through each rows one by one
            
            StringBuilder sql = new StringBuilder();
            sql.append( createHeaderText() );
            
            StringBuilder cellValue = new StringBuilder();
            sql.append( "INSERT INTO channel(populationId, concessionTypeId, concessionaireId, distinctive, " +
					  "channelBandId, physicChannel, power, latitude, longitude, acesli, effectiveDateStart, " +
            		  "effectiveDateEnd) VALUES\n" );
            
            String previousValue = null;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator(); //For each row, iterate through all the columns
                
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    int rowIndex = cell.getRowIndex();
                    
                    if( rowIndex < INITIAL_ROW ) {
                    	continue;
                    }
                    if( rowIndex > FINAL_ROW ) {
                    	break;
                    }
                    if( columnIndex == 2 ) {
                    	continue;
                    }
                    
                    boolean isNumericValue = cell.getCellType() == Cell.CELL_TYPE_NUMERIC ? true : false;
                    boolean isDigitalPair  = false;
                    cellValue = new StringBuilder();
                    
                    switch( columnIndex ) {
                    	case COLUMN_NUMBER:
                    		if( isNumericValue ) {
                    			cellValue.append( cell.getNumericCellValue()  );
                    		}
                    		else {
                    			cellValue.append( cell.getStringCellValue() );
                    		}
                    		break;
						case COLUMN_POPULATION:
							cellValue.append( populationsCollection.getPopulationPk( cell.getStringCellValue() ) );
							break;
						case COLUMN_CONCESSION_TYPE:
							cellValue.append( concessionTypeCollection.getConcessionTypePk( cell.getStringCellValue() ) );
							break;
						case COLUMN_CONCESSIONAIRE:
							cellValue.append( concessionaireCollection.getConcessionairePk( cell.getStringCellValue() ) );
							break;
						case COLUMN_DISTINCTIVE:
							cellValue.append( cell.getStringCellValue() );
							break;
						case COLUMN_CHANNEL_BAND:
							cellValue.append( channelBandCollection.getChannelBandPk( cell.getStringCellValue() ) );
							break;
						case COLUMN_PHYSIC_CHANNEL:
							if( isNumericValue ) {
								cellValue.append( cell.getNumericCellValue() );
							}
							else {
								cellValue.append( cell.getStringCellValue() );
							}
							break;
						case COLUMN_POWER:
							if( isNumericValue ) {
								cellValue.append( cell.getNumericCellValue() );
							}
							else {
								cellValue.append( cell.getStringCellValue() );
							}
							break;
						case COLUMN_LATITUDE:
							if( isNumericValue ) {
								cellValue.append( cell.getNumericCellValue() );
							}
							else {
								cellValue.append( cell.getStringCellValue() );
							}
							break;
						case COLUMN_LONGITUDE:
							if( isNumericValue ) {
								cellValue.append( cell.getNumericCellValue() );
							}
							else {
								cellValue.append( cell.getStringCellValue() );
							}
							break;
						case COLUMN_ACESLI:
							if( isNumericValue ) {
								cellValue.append( cell.getNumericCellValue() );
							}
							else {
								cellValue.append( cell.getStringCellValue() );
							}
							break;
						case COLUMN_EFFECTIVE_DAY_START:
							if( isNumericValue ) {
								if( HSSFDateUtil.isCellDateFormatted( cell ) ) {
									cellValue.append( parseToMySQLDate( cell.getDateCellValue().toString() ) );
								}
								else {
									cellValue.append( cell.getNumericCellValue() );
								}
							}
							else {
								cellValue.append( cell.getStringCellValue() );
							}
							break;
						case COLUMN_EFFECTIVE_DAY_END:
							if( isNumericValue ) {
								if( HSSFDateUtil.isCellDateFormatted( cell ) ) {
									cellValue.append( parseToMySQLDate( cell.getDateCellValue().toString() ) );
								}
								else {
									cellValue.append( cell.getNumericCellValue() );
								}
							}
							else {
								cellValue.append( cell.getStringCellValue() );
							}
							break;
                    }
                    
                    cellValue.replace( 0 , cellValue.length(), cellValue.toString().trim() ); //Remove white spaces at start and end
                    
                    //Format value if is a numeric value
                    if( isNumericValue ) {
                    	if( cellValue.toString().contains( "." ) ) {
                    		cellValue.replace( 0,
                    						   cellValue.length(),
                    						   cellValue.toString().replaceAll( "0*$", "" ).replaceAll( "\\.$", "" ) );
                    	}
                    }
                    
                    //Create insert sentence
                    if( columnIndex >= COLUMN_NUMBER && columnIndex <= COLUMN_EFFECTIVE_DAY_END ) {
                    	if( !cellValue.toString().isEmpty() ) {
                    		previousValue = cellValue.toString();
                    	}
                    	
                        if(  columnIndex == COLUMN_NUMBER ) { //Comment the number column
                        	cellValue.insert( 0, "/* " ).append( " */ " );
                        }
                        
                    	if( columnIndex == COLUMN_POPULATION ) {
                			cellValue.insert( 0, "('" ).append( "', '" );
 	                    }
 	                    else {
 	                    	if( columnIndex == COLUMN_EFFECTIVE_DAY_END ) {
 	                    		if( cellValue.toString().isEmpty() ) {
 	                    			cellValue.replace( 0, cellValue.length(), previousValue );
 	                    		}
 	                    		
 	                    		if( rowIndex < FINAL_ROW ) {
 	                    			cellValue.append( "' ),\n" );
 	                    		}
 	                    		else {
 	                    			cellValue.append( "' );\n" );
 	                    		}
 	                    	}
 	                    	else {
 	                    		if( columnIndex != COLUMN_NUMBER ) { 
 	                    			cellValue.append( "', '" );
 	                    		}
 	                    	}
 	                    }
                    	
                    	sql.append( cellValue.toString() );
                    }
                } //End of col iteration
                
                previousValue = null;
            } //End of row iterator
            workbook.close();
            file.close();

            new Writer( FILE_NAME ).write( sql.toString() );
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
		finally {
			System.out.println( "Importación finalizada: " + (new Date()) );
		}
	}
	
	private String createHeaderText() {
		return "/*\n"+
			   " * Generated on: " + (new Date()).toString() + "\n" +
			   " */\n\n" +
			   "USE tdt;\n\n";
	}
	
	private String parseToMySQLDate(String dateString) {
		String[] parts = dateString.split( " " );
		
		switch( parts[1].toLowerCase() ) {
			case "jan":
				parts[1] = "01"; 
				break;
			case "feb":
				parts[1] = "02"; 
				break;
			case "mar":
				parts[1] = "03"; 
				break;
			case "apr":
				parts[1] = "04"; 
				break;
			case "may":
				parts[1] = "05"; 
				break;
			case "jun":
				parts[1] = "06"; 
				break;
			case "jul":
				parts[1] = "07"; 
				break;
			case "aug":
				parts[1] = "08"; 
				break;
			case "sep":
				parts[1] = "09"; 
				break;
			case "oct":
				parts[1] = "10"; 
				break;
			case "nov":
				parts[1] = "11"; 
				break;
			case "dec":
				parts[1] = "12"; 
				break;
		}
		
		return parts[5] +"-"+ parts[1] +"-"+ parts[2];
	}
}
