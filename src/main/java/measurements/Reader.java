package measurements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.javatuples.Pair;

public class Reader {

	private static InputStream loadFile(String fileName) {
		InputStream inp = null;
		try {
			inp = new FileInputStream(Constants.Paths.SourcePath + fileName);
			System.out.println(String.format("File:%s parse finished.",
					fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("hooop");
		}
		return inp;
	}

	private static Workbook createWorkbook(InputStream inp) {
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(inp);
			System.out.println("oleeyy");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wb;
	}

	public static List<Sheet> getSheets(Workbook wb) {
		int numberOfSheets = wb.getNumberOfSheets();
		List<Sheet> sheets = new ArrayList<Sheet>();
		for (int i = 0; i < numberOfSheets; i++) {
			sheets.add(wb.getSheetAt(i));
		}
		return sheets;
	}

	public static List<Pair<Row, Row>> loadPairs(Sheet sheet) {
		List<Pair<Row, Row>> rowPairs = new ArrayList<Pair<Row, Row>>();
		int lastRowNum = sheet.getLastRowNum();
		System.out.println("There is " + lastRowNum + " rows in sheet");
		for (int i = 0; i < lastRowNum; i ++) {

			Row row = sheet.getRow(i);
			Row nextRow = sheet.getRow(i+1);
			if(null == row)
			{
				// quit if row not exists..
				System.out.println("Row [" + i + "] is empty");
				continue;
			}
			
			if(null == nextRow)
			{
				// quit if next row not exists..
				System.out.println("Row [" + (i + 1) + "] is empty");
				continue;
			}

			if( isHolidayLine(row) )
			{
				// Empty row found.. jump to next..
				System.out.println("Row [" + i + "] is holiday");
				continue;
			}
			
			if(isRowContainsNextDay(nextRow))
			{
				if( isHolidayLine(nextRow) )
				{
					// Next row is holiday...
					System.out.println("Row [" + (i + 1) + "] is holiday");
					continue;
				}
				else
				{
					// Next row is date...
					System.out.println("Row [" + (i + 1) + "] is work day");
					continue;
				}
			}
			
			if(isRowHasFutureDate(row))
			{
				System.out.println("Row [" + (i + 1) + "] has future date. Breaking loop.");
				break;
			}
			
			String firstCellValue = getFirstCellValue(row);
			System.out.println("Loading row[" + i + "] and row[" + (i+1) + "] from sheet.. First Cell Value:"+firstCellValue);

			Pair<Row, Row> pair = new Pair<Row, Row>(row,nextRow);
			rowPairs.add(pair);

			System.out.print("Double row read.. continue with next...");
			// Double row read.. continue with next..
			i++;
		}
		return rowPairs;
	}
	
	private static String getFirstCellValue(Row row)
	{
		List<Cell> cells = loadCells(row);
		Cell cell = cells.get(0);
		cell.getDateCellValue();
		return cell.getDateCellValue().toString();
	}

	private static boolean isHolidayLine(Row row)
	{
		List<Cell> cells = loadCells(row);
		if(cells == null || cells.size()==0)
		{
			System.out.println("Row not contains cells");
			return false;
		}
		
		System.out.println("Row contains " + cells.size() + " cells");

		Cell cell = cells.get(1);
		int cellType = cell.getCellType();
		if(HSSFCell.CELL_TYPE_BLANK == cellType)
		{
			System.out.println("Cell is EMPTY");
			return false;
		}

		String value = cell.getStringCellValue();
		if("Pazar".equals(value))
		{
			System.out.println("Cell is SUNDAY:"+value);
			return true;
		}
		return false;
	}

	private static boolean isRowContainsNextDay(Row row) {
		List<Cell> cells = loadCells(row);
		Cell cell = cells.get(0); // Not : date alanõ ilk kolon
		Date value = cell.getDateCellValue();
		if (null != value) {
			System.out.println("Next line has date field:" + value);
			return true;
		}
		return false;
	}
	
	private static boolean isRowHasFutureDate(Row row) {
		List<Cell> cells = loadCells(row);
		Cell cell = cells.get(0); // Not : date alanõ ilk kolon
		Date value = cell.getDateCellValue();
		if (null != value) {
			System.out.println("Line has date field:" + value);
			if( value.after(new Date()) )
			{
				System.out.println("Line has date field:" + value + " and is future date");
				return true;
			}
		}
		return false;
	}
	
	private static List<Cell> loadCells(Row row)
	{
		List<Cell> cells = new ArrayList<Cell>();
		short lastCellNum = row.getLastCellNum();
		for(short i = 0; i<lastCellNum; i++)
		{
			cells.add(row.getCell(i));
		}
		return cells;
	}
	
	private static Date readDate(Pair<Row, Row> rowPair)
	{
		Row firstRow = rowPair.getValue0();
		List<Cell> cells = loadCells(firstRow);
		Cell cell = cells.get(0);
		Date date = cell.getDateCellValue();
		return date;
	}
	
	private static List<Pair<String, String>> readWorkLogs(Pair<Row, Row> row)
	{
		List<Pair<String, String>> logs = new ArrayList<Pair<String,String>>();
		System.out.println(readDate(row) + " WORKLOG reading...");
		
		Row measurementDetailsRow = row.getValue0();
		Row enstrumentAndWorkerDetailsRow = row.getValue1();

		List<Cell> measurementDetailsCells = loadCells(measurementDetailsRow);
		List<Cell> enstrumentAndWorkerDetailsCells = loadCells(enstrumentAndWorkerDetailsRow);
		System.out.println("Row has " + measurementDetailsCells.size() + "cells.");
		System.out.println("Row has " + enstrumentAndWorkerDetailsCells.size() + "cells.");
		for(int i = 2; i<measurementDetailsCells.size(); i++)
		{
			String measurementDetail = "";
			if(null != measurementDetailsCells.get(i))
			{
				measurementDetail = measurementDetailsCells.get(i).getStringCellValue();	
			}
			
			String enstrumentDetail = "";
			if(enstrumentAndWorkerDetailsCells.size() > i)
			{
				if(null != enstrumentAndWorkerDetailsCells.get(i))
				{
					enstrumentDetail = enstrumentAndWorkerDetailsCells.get(i).getStringCellValue();	
				}
					
			}
			
			Pair<String, String> log = new Pair<String, String>(measurementDetail, enstrumentDetail);
			logs.add(log);
		}
		return logs;
	}
	
	public static void main(String[] args) {
		InputStream inp = loadFile("excel-25-06-2014.xls");
		Workbook wb = createWorkbook(inp);
		List<Sheet> sheets = getSheets(wb);
		Sheet sheet = sheets.get(0);
		List<Pair<Row, Row>> rowPairs = loadPairs(sheet);
		for(Pair<Row, Row> rowPair:rowPairs)
		{
			Date date = readDate(rowPair);
			System.out.println("Date of row is " + date);
			List<Pair<String, String>> dailyWorkLogs = readWorkLogs(rowPair);
			for(Pair<String,String> workLog:dailyWorkLogs)
			{
				System.out.println("*** " + date + " ****");
				System.out.println(workLog.getValue0());
				System.out.println("----------");
				System.out.println(workLog.getValue1());
			}
		}
		
		/*
		Cell cell = row.getCell(3);
		if (cell == null)
			cell = row.createCell(3);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue("a test");

		// Write the output to a file
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(
					"/Users/ramazana/Desktop/HALIC CEVRE/excel-25-06-2014-out.xls");
			System.out.println("oleeyy");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		try {
			wb.write(fileOut);
			System.out.println("oleeyy");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		try {
			fileOut.close();
			System.out.println("oleeyy");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		*/
	}
}
