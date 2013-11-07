package com.example.OtakuCollect.framework;

import android.content.ContentValues;

public interface FileIO {
	// public InputStream readAsset(String fileName) throws IOException;
	//
	// public InputStream readFile(String fileName) throws IOException;
	//
	// public OutputStream writeFile(String fileName) throws IOException;

	public void CreateDB();
	
	public void createTable();
	
	public void writeFile(ContentValues val);
	
	public void readFile();
	
	public String[] getNames();
	
	public String[] getScores();
	
	public int getRecode();
}
