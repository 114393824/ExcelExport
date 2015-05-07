/**
*@author 陈小虎
*@created on 2014-12-26
*/

package com.emagroup.excelexport.tofile;

import java.util.Vector;

public abstract class CustomFile {
	
	protected abstract void toFile(Vector<String> vec,String exportPath);
}

