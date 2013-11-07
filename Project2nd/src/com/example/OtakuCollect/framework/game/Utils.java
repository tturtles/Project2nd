package com.example.OtakuCollect.framework.game;

import android.content.ContentValues;
import android.provider.MediaStore.Files;
import android.util.Log;

import com.example.OtakuCollect.framework.FileIO;
import com.example.OtakuCollect.framework.Input.TouchEvent;

public class Utils {
	public static boolean soundEnabled = true;
	public static int[] highscores = new int[] { 100, 80, 50, 30, 10 };

	public Utils() {

	}

	public static void load(FileIO files) {
		files.CreateDB();
		files.createTable();
	}

	public static void addscore(FileIO files, String name, int score) {
		ContentValues val = new ContentValues();
		val.put("name", name);
		val.put("score", score);
		files.writeFile(val);
	}
	
	public static void readFile(FileIO files) {
		files.readFile();
	}
	
	public static int getRecode(FileIO files) {
		Log.d("レコード数", ""+files.getRecode());
		return files.getRecode();
	}
	
	public static String[] getNames(FileIO files) {
		return files.getNames();
	}
	
	public static String[] getScores(FileIO files) {
		return files.getScores();
	}

	// public static void load(FileIO files) {
	// BufferedReader in = null;
	// try {
	// in = new BufferedReader(new InputStreamReader(
	// files.readFile(".animalrun_savedata")));
	// soundEnabled = Boolean.parseBoolean(in.readLine());
	// for (int i = 0; i < 5; i++) {
	// highscores[i] = Integer.parseInt(in.readLine());
	// }
	// } catch (IOException e) {
	// // デフォルト設定があるのでエラーは無視
	// } catch (NumberFormatException e) {
	// // 同上
	// } finally {
	// try {
	// if (in != null)
	// in.close();
	// } catch (IOException e) {
	// }
	// }
	// }

	// public static void save(FileIO files) {
	// BufferedWriter out = null;
	// try {
	// out = new BufferedWriter(new OutputStreamWriter(
	// files.writeFile(".animalrun_savedata")));
	// out.write(Boolean.toString(soundEnabled));
	// for (int i = 0; i < 5; i++) {
	// out.write(Integer.toString(highscores[i]));
	// }
	// } catch (IOException e) {
	// } finally {
	// try {
	// if (out != null)
	// out.close();
	// } catch (IOException e) {
	// }
	// }
	// }
	//
	// public static void addScore(int score) {
	// for (int i = 0; i < 5; i++) {
	// if (highscores[i] < score) {
	// for (int j = 4; j > i; j--)
	// highscores[j] = highscores[j - 1];
	// highscores[i] = score;
	// break;
	// }
	// }
	// }

	public boolean isBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

}
