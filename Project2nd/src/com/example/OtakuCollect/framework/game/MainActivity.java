package com.example.OtakuCollect.framework.game;

import com.example.OtakuCollect.framework.Screen;
import com.example.OtakuCollect.framework.impl.AndroidGame;

public class MainActivity extends AndroidGame {

	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}

}