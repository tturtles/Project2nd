package com.example.OtakuCollect.framework.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class AndroidFileIO implements com.example.OtakuCollect.framework.FileIO {
	Context con;
	CreateProductHelper helper = null;
	SQLiteDatabase db = null;

	// String externalStoragePath;

	public AndroidFileIO(Context con) {
		this.con = con;
		// this.externalStoragePath = Environment.getExternalStorageDirectory()
		// .getAbsolutePath() + File.separator;
	}

	public void CreateDB() {
		helper = new CreateProductHelper(con);
		db = helper.getWritableDatabase();
	}

	public void createTable() {
		try {
			String sql = "create table priduct("+"_id integer primary key autoincrement,"+"name text not null," + "score integer default 0)";
			db.execSQL(sql);
			Log.d("テーブル作成", "成功");
		} catch (Exception e) {
			Log.d("テーブル作成", "作成済み");
			Log.d("error", e.toString());
		}
	}
	
	public void writeFile(ContentValues val) {
		try {
			db.beginTransaction();			// トランザクション制御開始
			db.insert("name", null, val);	// データ登録
			db.setTransactionSuccessful();	// コミット
			db.endTransaction();			// トランザクション制御終了
			Log.d("データ登録", "成功");
		}catch (Exception e) {
			Log.d("データ登録", "失敗");
			Log.d("error", e.toString());
		}
	}
	
	public void readFile() {
		try {
			db = helper.getReadableDatabase();
			String columns[] = {"priductid", "name", "score"};
			/* ここから記述開始 */
		}
	}
	

	// public InputStream readAsset(String fileName) throws IOException {
	// return assets.open(fileName);
	// }
	//
	// public InputStream readFile(String fileName) throws IOException {
	// return new FileInputStream(externalStoragePath + fileName);
	// }
	//
	// public OutputStream writeFile(String fileName) throws IOException {
	// return new FileOutputStream(externalStoragePath + fileName);
	// }

}
