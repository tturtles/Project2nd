package com.example.OtakuCollect.framework.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.OtakuCollect.framework.FileIO;

public class AndroidFileIO implements FileIO {
	Context con;
	CreateProductHelper helper = null;
	SQLiteDatabase db = null;

	public AndroidFileIO(Context con) {
		this.con = con;
	}

	public boolean CreateDBandTable(String sql) {
		helper = new CreateProductHelper(con);
		db = helper.getWritableDatabase();
		try {
			db.execSQL(sql);
			Log.d("テーブル作成", "成功");
			return true;
		} catch (Exception e) {
			Log.d("テーブル作成", "作成済み");
			Log.d("error", e.toString());
			return false;
		}
	}

	public boolean writeFile(ContentValues val) {
		try {
			db = helper.getWritableDatabase();
			db.beginTransaction(); // トランザクション制御開始
			db.insert("score_data", null, val); // データ登録
			db.setTransactionSuccessful(); // コミット
			Log.d("データ登録", "成功");
			return true;
		} catch (Exception e) {
			Log.d("データ登録", "失敗");
			Log.d("error", e.toString());
			return false;
		} finally {
			db.endTransaction(); // トランザクション制御終了
			db.close();
		}
	}

	public String[][] readFile(String[] columns, String older, int quantity) {
		String[][] list = new String[quantity][columns.length];
		try {
			db = helper.getReadableDatabase();
			Cursor cursor = db.query("score_data", columns, null, null, null,
					null, older);
			for (int i = 0; i < quantity && cursor.moveToNext(); i++)
				for (int j = 0; j < columns.length; j++)
					list[i][j] = cursor.getString(j);
			Log.d("データ取得", "成功");
		} catch (Exception e) {
			Log.d("データ取得", "失敗");
			Log.d("error", e.toString());
		} finally {
			db.close();
		}
		if (list[0][1] == null)
			return null;
		else
			return list;
	}

}
