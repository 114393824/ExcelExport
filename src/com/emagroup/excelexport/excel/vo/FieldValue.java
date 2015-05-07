/**
*@author 陈小虎
*@created on 2014-12-26
*/

package com.emagroup.excelexport.excel.vo;

import java.util.HashMap;
import java.util.Vector;

public class FieldValue {
	private HashMap<String,Vector<String>> m_mFieldValue = new HashMap<String,Vector<String>>();
	
	public void addFieldValue(String key,Vector<String> values){
		this.m_mFieldValue.put(key, values);
	}
	
	public Vector<String> getFieldValue(String key){
		return this.m_mFieldValue.get(key);
	}
	
	public HashMap<String,Vector<String>> getFieldValues(){
		return this.m_mFieldValue;
	}
	
	public void clear(){
		this.m_mFieldValue.clear();
	}
}

