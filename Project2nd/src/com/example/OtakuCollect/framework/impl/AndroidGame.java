package com.example.OtakuCollect.framework.impl;

import com.example.OtakuCollect.framework.Audio;
import com.example.OtakuCollect.framework.FileIO;
import com.example.OtakuCollect.framework.Game;
import com.example.OtakuCollect.framework.Graphics;
import com.example.OtakuCollect.framework.Input;
import com.example.OtakuCollect.framework.Screen;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

public abstract class AndroidGame extends Activity implements Game {
	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;
	RelativeLayout mainLayout;
	EditText et;
	Handler handler;
	final int WIDTH = 300;
	final int HEIGHT = 100;
	final int ET_X = 90;
	final int ET_Y = 460;
	final int ET_BUCKCOLOR = Color.GRAY;
	final int ET_TEXTCOLOR = Color.WHITE;
	final int ET_TEXTSIZE = 30;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 800 : 480;
		int frameBufferHeight = isLandscape ? 480 : 800;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
				frameBufferHeight, Config.RGB_565);
		float scaleX = (float) frameBufferWidth
				/ getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferHeight
				/ getWindowManager().getDefaultDisplay().getHeight();
		renderView = new AndroidFastRenderView(this, frameBuffer);
		graphics = new AndroidGraphics(getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(this);
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);
		screen = getStartScreen();

		/* 試験的 */
		float _scaleX = getWindowManager().getDefaultDisplay().getWidth()
				/ (float) frameBufferWidth;
		float _scaleY = getWindowManager().getDefaultDisplay().getHeight()
				/ (float) frameBufferHeight;
		Log.d("SCALE", "scaleX" + _scaleX + "scaleY" + _scaleY);
		float density = getResources().getDisplayMetrics().density;
		/* End */

		/* 試験的 */
		handler = new Handler();
		mainLayout = new RelativeLayout(this);
		mainLayout.addView(renderView);
		et = new EditText(this);
		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(
				(int)((float)WIDTH*_scaleX), (int)((float)HEIGHT*_scaleY));
//		param.setMargins((int)((float)ET_X*density+0.5f), (int)((float)ET_Y*density+0.5f), 0, 0);
		InputFilter[] inputFilter = new InputFilter[1];
		inputFilter[0] = new InputFilter.LengthFilter(5);	// EditTextの文字数制限
		et.setFilters(inputFilter);
		param.setMargins((int)((float)ET_X*_scaleX+0.5f), (int)((float)ET_Y*_scaleY+0.5f), 0, 0);
		mainLayout.addView(et, param);
		et.setBackgroundColor(ET_BUCKCOLOR);
		et.setTextColor(ET_TEXTCOLOR);
		et.setTextSize(ET_TEXTSIZE);
		et.setInputType(InputType.TYPE_CLASS_TEXT);
		setContentView(mainLayout);
		/* End */

		// setContentView(renderView);

		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
				"GLGame");
	}

	@Override
	public void onResume() {
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}

	@Override
	public void onPause() {
		super.onPause();
		wakeLock.release();
		renderView.pause();
		screen.pause();

		if (isFinishing())
			screen.dispose();
	}

	public Input getInput() {
		return input;
	}

	public FileIO getFileIO() {
		return fileIO;
	}

	public Graphics getGraphics() {
		return graphics;
	}

	public Audio getAudio() {
		return audio;
	}

	public void setScreen(Screen screen) {
		if (screen == null)
			throw new IllegalArgumentException("Screen must not be null");

		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}

	public Screen getCurrentScreen() {
		return screen;
	}

	/* 9/1Wiget追加類（レイアウトいじるよう ） */

	public void chengeEditText(final boolean swith) {
		new Thread(new Runnable() {
			public void run() {
				handler.post(new Runnable() {
					public void run() {
						if (swith)
							et.setVisibility(View.VISIBLE);
						else
							et.setVisibility(View.GONE);
					}
				});
			}
		}).start();
	}

	public String getEText() {
		return et.getText().toString();
	}

	/* End */
}
