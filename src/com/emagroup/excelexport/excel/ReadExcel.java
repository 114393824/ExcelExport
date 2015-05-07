/**
 *@author 陈小虎
 *@created on 2014-12-26
 */

package com.emagroup.excelexport.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.emagroup.excelexport.common.Common;
import com.emagroup.excelexport.excel.vo.DataManager;
import com.emagroup.excelexport.toprogram.ProgramManager;
import com.emagroup.excelexport.util.Util;

public class ReadExcel {
	
	private static int SPACE_LINE = 0;

	public void readExcel(String path,String line) {
		
		SPACE_LINE = Integer.parseInt(line);
		
		if (path == null || Common.EMPTY.equals(path)) {

		} else {
			String postfix = Util.getPostfix(path);

			if (!Common.EMPTY.equals(postfix)) {
				if (Common.OFFICE_EXCEL_POSTFIX_XLS.equals(postfix)) {
					readXls(path);
				} else if (Common.OFFICE_EXCEL_POSTFIX_XLSX.equals(postfix)) {
					readXlsx(path);
				}
			}
		}
	}

	/**
	 * Read the file of xls
	 * 
	 * @param path
	 *            the path of the Excel file
	 * 
	 */
	public void readXls(String path) {
		System.out.println(Common.PROCESSING + path);
		System.out.println("***************************************");
		try {
			InputStream is = new FileInputStream(path);

			HSSFWorkbook book = new HSSFWorkbook(is);

			// Read the sheet
			for (int numSheet = 0; numSheet < book.getNumberOfSheets(); numSheet++) {
				if(numSheet >= 1){
					break;
				}
				DataManager.getInstance().clear();
				
				HSSFSheet sheet = book.getSheetAt(numSheet);

				if (sheet == null) {
					continue;
				}
				
				String sheetName = sheet.getSheetName();
				
				System.out.println("readXls  sheetName = " + sheetName);

				// Read the row
				for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
					if(rowNum > 0 && rowNum <= SPACE_LINE){
						continue;
					}
					System.out.println("");
					HSSFRow row = sheet.getRow(rowNum);

					if (row != null) {
						Vector<String> vec = new Vector<String>();
						// Read the cell
						for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
							HSSFCell cell = row.getCell(cellNum);
							if (cell != null) {
								String cellValue = getValue(cell);
								System.out.print(cellValue + " ");
								
								if(cellValue.length() > 0){
									if (rowNum == 0) {
										DataManager.getInstance().addFieldName(
												cellValue);
									} else {
										vec.add(cellValue);
									}
								}else
								 {
									 vec.add("null");
								 }


							}else{

								if (rowNum == 0) {
									DataManager.getInstance().addFieldName(
											"null");
								} else {
									vec.add("null");
								}
							}

							// String name = CellReference
							// .convertNumToColString(cellNum);
							// System.out.println("name = " + name);
						}

						if(rowNum - SPACE_LINE > 0){
							boolean add = false;
							for(int i = 0; i < vec.size(); i++){
								String value = vec.get(i); 
								if(!value.equals("null")){
									add = true;
								}
							}
							if(add){
								DataManager.getInstance().addFieldValue(String.valueOf(rowNum - SPACE_LINE), vec);
							}
							
						}
					}
				}

				ProgramManager.getInstance().toSqlite(sheetName);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * read the file of xlsx
	 * 
	 * @param path
	 *            the path of the Excel file
	 */
	public void readXlsx(String path) {
		System.out.println(Common.PROCESSING + path);

		try {
			InputStream is = new FileInputStream(path);
			XSSFWorkbook book = new XSSFWorkbook(is);

			// Read the sheet
			for (int numSheet = 0; numSheet < book.getNumberOfSheets(); numSheet++) {
				if(numSheet >= 1){
					break;
				}
				
				DataManager.getInstance().clear();
				
				XSSFSheet sheet = book.getSheetAt(numSheet);
				if (sheet == null) {
					continue;
				}
				
				String sheetName = sheet.getSheetName();
				
				System.out.println("readXlsx  sheetName = " + sheetName);

				// Read the row
				for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
					
					if(rowNum > 0 && rowNum <= SPACE_LINE){
						continue;
					}
					System.out.println("");
					XSSFRow row = sheet.getRow(rowNum);
					if (row != null) {
						Vector<String> vec = new Vector<String>();
						// Read the cell
						for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
							

							
							XSSFCell cell = row.getCell(cellNum);
							if (cell != null) {
								String cellValue = getValue(cell);
								 System.out.print(cellValue + " ");
								 
								 if(cellValue.length() > 0){
										if (rowNum == 0) {
											DataManager.getInstance().addFieldName(
														cellValue);
										} else {
											vec.add(cellValue);
										} 
								 }else
								 {
									 vec.add("null");
								 }
									 


							}else{
								if (rowNum == 0) {
									DataManager.getInstance().addFieldName(
											"null");
								} else {
									vec.add("null");
								}
							}
						}
						
						if(rowNum - SPACE_LINE > 0){
							boolean add = false;
							for(int i = 0; i < vec.size(); i++){
								String value = vec.get(i); 
								if(!value.equals("null")){
									add = true;
								}
							}
							if(add){
								DataManager.getInstance().addFieldValue(String.valueOf(rowNum - SPACE_LINE), vec);
							}
							
						}
					}
				}
				
				ProgramManager.getInstance().toSqlite(sheetName);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getValue(XSSFCell cell) {
		if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			return String.valueOf((int)cell.getNumericCellValue());
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}

	private String getValue(HSSFCell cell) {
		if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			return String.valueOf((int)cell.getNumericCellValue());
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}
}
