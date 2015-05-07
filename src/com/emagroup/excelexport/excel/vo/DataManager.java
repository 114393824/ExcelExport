/**
*@author 陈小虎
*@created on 2014-12-26
*/

package com.emagroup.excelexport.excel.vo;

import java.util.Vector;

public class DataManager {
	
	private static DataManager mInstance = new DataManager();
	
	private FieldName mFieldName;
	private FieldValue mFieldValue;
	
	private DataManager(){
		mFieldName = new FieldName();
		mFieldValue = new FieldValue();
	}
	
	public static DataManager getInstance(){
		return mInstance;
	}
	
	public void clear(){
		this.mFieldName.clear();
		this.mFieldValue.clear();
	}
	
	public void addFieldName(String name){
		this.mFieldName.addFieldName(name);
	}
	
	public FieldName getFieldName(){
		return this.mFieldName;
	}
	
	public void addFieldValue(String key,Vector<String> values){
		this.mFieldValue.addFieldValue(key, values);
	}
	
	public FieldValue getFieldValue(){
		return this.mFieldValue;
	}
}

