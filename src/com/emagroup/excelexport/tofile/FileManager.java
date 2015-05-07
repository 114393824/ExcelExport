/**
*@author 陈小虎
*@created on 2014-12-26
*/

package com.emagroup.excelexport.tofile;

import java.util.Vector;

public class FileManager {
	private static FileManager mInstance = new FileManager();
	
	String mExportPath;
	
	private Text mText;
	private FileManager(){
		mText = new Text();
	}
	
	public static FileManager getInstance(){
		return mInstance;
	}
	
	public void setExportPath(String path){
		mExportPath = path;
	}
	
	public void toText(Vector<String> set){
		this.mText.toFile(set,mExportPath);
	}
}

