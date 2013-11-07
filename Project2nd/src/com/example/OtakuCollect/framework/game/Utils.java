package com.example.OtakuCollect.framework.game;

import android.content.ContentValues;
import android.provider.MediaStore.Files;
import android.util.Log;

import com.example.OtakuCollect.framework.FileIO;
import com.example.OtakuCollect.framework.Input.TouchEvent;

public class Utils {
	public static boolean soundEnabled = true;

	public static void load(FileIO files) {
		String sql = "create table score_data("+"_id integer primary key autoincrement,"+"name text not null," + "score integer default 0)";
		files.CreateDBandTable(sql);
	}

	public static boolean addscore(FileIO files, String name, int score) {
		if(name.toString().equals("")) return false;
		ContentValues val = new ContentValues();
		val.put("name", name);
		val.put("score", score);
		return files.writeFile(val);
	}
	
	public static String[][] readFile(FileIO files) {
		String[] columns = {"name", "score"};
		String order =  "score desc";
		return files.readFile(columns, order, 5);
	}
	
}
