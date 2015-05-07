/**
*@author 陈小虎
*@created on 2014-12-26
*/

package com.emagroup.excelexport.toprogram;

public abstract class Program {
	protected String mExportFileName = "Default.txt";
	
	protected abstract void toProgram(String name);
	
	protected void setExportFileName(String name){
		this.mExportFileName = name;
	};
}

