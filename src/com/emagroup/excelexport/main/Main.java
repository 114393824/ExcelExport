/**
*@author 陈小虎
*@created on 2014-12-26
*/

package com.emagroup.excelexport.main;


import com.emagroup.excelexport.excel.ReadExcel;
import com.emagroup.excelexport.tofile.FileManager;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < args.length; i++){
			System.out.println("main args["+i+"] = " + args[i]);
		}
		if(args.length < 3){
			return;
		}
		
		String line = args[0];
		String xlsPath = args[1];
		String filePath = args[2];
		
		FileManager.getInstance().setExportPath(filePath);
		
		ReadExcel re = new ReadExcel();
		re.readExcel(xlsPath,line);
	}

}
