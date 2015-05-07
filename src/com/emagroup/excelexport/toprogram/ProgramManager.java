/**
*@author 陈小虎
*@created on 2014-12-26
*/

package com.emagroup.excelexport.toprogram;

public class ProgramManager {

	private static ProgramManager mInstance = new ProgramManager();
	private SQLite mSQLite;
	private Json mJson;
	private Xml mXml;
	
	private ProgramManager(){
		mSQLite = new SQLite();
		mJson = new Json();
		mXml = new Xml();
	}
	
	public static ProgramManager getInstance(){
		return mInstance;
	}
	
	public void toSqlite(String sheetName){
		//new Thread().run();
		mSQLite.toProgram(sheetName);
	}
	
	public void toJson(String sheetName){
		mJson.toProgram(sheetName);
	}
	
	public void toXml(String sheetName){
		mXml.toProgram(sheetName);
	}
}

