package com.automation.utility;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiXlsxFileUtility {
	
	
	private static XSSFWorkbook workbook=null;
	
	public static Object readSingleCellDataFromXLfile(File path, String sheetName, int rowNum, int cellNum) {
		Object data=null;
		//XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(path);
			System.out.println(workbook.getSheetName(0));
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(rowNum);
			XSSFCell cell = row.getCell(cellNum);
			
			if (cell.getCellType() == CellType.NUMERIC)
				data= (Object)Double.valueOf(cell.getNumericCellValue());
				//System.out.println("data is=" + cell.getNumericCellValue());
			else if (cell.getCellType() == CellType.STRING)
				data= (Object)cell.getStringCellValue();
				//System.out.println("data is=" + cell.getStringCellValue());

		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}
	public static int getTotalRowsCount(XSSFSheet sheet) {
		return sheet.getLastRowNum()+1;
	}
	
	public static int getTotalCellCount(XSSFRow row) {
		return row.getLastCellNum();
	}

	public static void readAllCellDataFromXLfile(File path, String sheetName) {
		Object[][] ob=new Object[10][20];
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(path);
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int RowTotal=getTotalRowsCount(sheet);
		Iterator<Row> rows = sheet.iterator();
		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.iterator();
			while (cells.hasNext()) {
				Cell cell = cells.next();
				if (cell.getCellType() == CellType.NUMERIC)
					System.out.print(cell.getNumericCellValue() + "  ");
				else if (cell.getCellType() == CellType.STRING)
					System.out.print(cell.getStringCellValue() + "  ");

			}
			System.out.println();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static Object[][] readAllCellDataFromXLfile(String path,String sheetName) throws IOException {
		FileInputStream fs = new FileInputStream(new File(path));

		XSSFWorkbook workbook = new XSSFWorkbook(fs);// .xlsx
		XSSFSheet sheet = workbook.getSheet(sheetName);	
		int rowCount = sheet.getLastRowNum()+1;
		int columnCount = sheet.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[rowCount][columnCount];
		Iterator<Row> rows= sheet.rowIterator();
		int i=0,j=0;
		while(rows.hasNext()) {
			Iterator<Cell> cells  =rows.next().cellIterator();
			j=0;
			while(cells.hasNext()) {
				XSSFCell cell=(XSSFCell) cells.next();
				if (cell.getCellType() == CellType.NUMERIC) {
					data[i][j]= cell.getNumericCellValue();
				}
				else if (cell.getCellType() == CellType.STRING) {
					data[i][j]=cell.getStringCellValue();
				}
				j++;
			}
			i++;
		}
		return data;	
		
	}

	public static void readAllCellDataFromAllXLSheets(File file) {
		
		
	}
	
	public static void writeXlsxCellData(File path,String sheetName,int row,int column,String data) {
		
		FileInputStream fs=null;
		//XSSFWorkbook workbook =null;
		FileOutputStream fo=null;
		try {
			fs = new FileInputStream(path);
			workbook= new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int sizeOfRow=sheet.getLastRowNum()+1;
			XSSFRow rowData = sheet.getRow(row);
			int sizeOfCell=rowData.getLastCellNum();
			XSSFCell cell = rowData.getCell(column);
			cell.setCellValue(data);
			fo = new FileOutputStream(path);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.write(fo);
			fs.close();
			fo.flush();
			fo.close();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println("completed writing");
	}
	
	public static void writeXlsxCellData(File path,String sheetName,int row,int column,double data) {
		FileInputStream fs=null;
	//	XSSFWorkbook workbook =null;
		FileOutputStream fo=null;
		try {
			fs = new FileInputStream(path);
			workbook= new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			XSSFRow rowData = sheet.getRow(row);
			XSSFCell cell = rowData.getCell(column);
			cell.setCellValue(data);
			fo = new FileOutputStream(path);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.write(fo);
			fs.close();
			fo.flush();
			fo.close();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println("completed writing");
	}
	
	public static void createsheetAndAddData(File path,String sheetName) {
		FileInputStream fi=null;
		//XSSFWorkbook workbook=null;
		try {
			fi=new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet newSheet=workbook.createSheet(sheetName);
		XSSFRow newRow= newSheet.createRow(0);
		XSSFCell cell1= newRow.createCell(0,CellType.STRING);
		XSSFCell cell2=newRow.createCell(1,CellType.NUMERIC);
	//	CellStyle style=cell1.getCellStyle();
	//	cell1.setCellStyle(null);
		cell1.setCellValue("java");
		cell2.setCellValue(9);
		FileOutputStream fo=null;
		try {
			fo = new FileOutputStream(path);
			workbook.write(fo);
			fi.close();
			fo.flush();
			fo.close();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("completed creating new sheet and added data");
		
			
		
	}

}