/**
*@author 陈小虎
*@created on 2014-12-26
*/

package com.emagroup.excelexport.toprogram;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import com.emagroup.excelexport.excel.vo.DataManager;
import com.emagroup.excelexport.tofile.FileManager;
import com.emagroup.excelexport.util.Util;

public class SQLite extends Program{
	
	private Vector<String> mVec = new Vector<String>();
	
	private String mTableName;
	
	private void createTable(){
		String dropTable = "drop table if exists \'" + this.mTableName + "\';";//'skill';";
		mVec.add(dropTable);
		
		System.out.println(dropTable);
		
		String sql = "create table \'" + this.mTableName + "\'";
		String colNames = "";//列名
		
		Vector<String> vec = DataManager.getInstance().getFieldName().getFieldNames();
		for(int i = 0; i < vec.size(); i++){
			String name = vec.get(i); 
			if(name.startsWith("_")){
				continue;
			}
			colNames += name + ",";  
			
		}

		sql += "(" + colNames.substring(0,colNames.length() - 1) + ");";

		System.out.println(sql);
		
		mVec.add(sql);
	}
	
	private void createInsert(Vector<String> lineValues){
		String sql = "insert into \'" + this.mTableName + "\'";
		String colNames = ""; //列名  
        String colValues = ""; //列值
        
        Vector<String> vec = DataManager.getInstance().getFieldName().getFieldNames();
        
        for(int i = 0; i < vec.size(); i++){
        	String name = vec.get(i);
			if(name.startsWith("_")){
				continue;
			}
        	String value = "null";
        	if(i < lineValues.size()){
        		value = lineValues.get(i);
        	}
        	
        	colNames += name + ",";
        	
        	if(Util.isNumber(value) || value.equals("null")){
        		colValues += value + ",";
        	}else{
        		colValues += "'" + value + "',";
        	}
        }
        
        //sql += "(" + colNames.substring(0,colNames.length() - 1) + ") values (" + colValues.substring(0,colValues.length() - 1) + ");";
        sql += " values(" + colValues.substring(0,colValues.length() - 1) + ");";
        mVec.add(sql);

        System.out.println(sql);

	}
	
	private void createAllInsert(){
		HashMap<String,Vector<String>> hashMap = DataManager.getInstance().getFieldValue().getFieldValues();
		//System.out.print("createAllInsert ---- hashMap.size() = " + hashMap.size());
		for(int i = 0; i < hashMap.size(); i++){
			Vector<String> vec = hashMap.get(String.valueOf(i + 1));
			//System.out.println("createAllInsert ---- vec.size() = " + vec.size());
			this.createInsert(vec);
		}
	}

	@Override
	protected void toProgram(String sheetName) {
		// TODO Auto-generated method stub
		this.mTableName = sheetName;
		
		this.createTable();
		this.createAllInsert();
		
		FileManager.getInstance().toText(mVec);
	}

}

