/**
 *@author 陈小虎
 *@created on 2014-12-26
 */

package com.emagroup.excelexport.tofile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class Text extends CustomFile {

	@Override
	protected void toFile(Vector<String> vec,String exportPath) {
		// TODO Auto-generated method stub
		File file = new File(exportPath);
		Iterator<String> iterator = vec.iterator();
		

		FileWriter fw = null;
		BufferedWriter writer = null;

		try {
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);
			while (iterator.hasNext()) {
				//writer.write(new String(iterator.next().toString().getBytes(),"utf-8"));
				writer.write(iterator.next().toString());
				writer.newLine();// 换行
			}
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
