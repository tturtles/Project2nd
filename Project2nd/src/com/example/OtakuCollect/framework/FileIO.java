package com.example.OtakuCollect.framework;

import android.content.ContentValues;

public interface FileIO {

	public boolean CreateDBandTable(String sql);
	
	public boolean writeFile(ContentValues val);
	
	public String[][] readFile(String[] columns, String older,int quantity);
	
}
