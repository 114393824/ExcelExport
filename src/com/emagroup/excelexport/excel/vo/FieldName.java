/**
*@author 陈小虎
*@created on 2014-12-26
*/

package com.emagroup.excelexport.excel.vo;

import java.util.Vector;

public class FieldName {
	
	private Vector<String> m_vFieldName = new Vector<String>();
	
	public void addFieldName(String name){
		this.m_vFieldName.add(name);
	}
	
	public Vector<String> getFieldNames(){
		return this.m_vFieldName;
	}
	
	public void clear(){
		this.m_vFieldName.clear();
	}
}

