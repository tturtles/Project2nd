package com.example.OtakuCollect.framework.game;

import java.util.List;

import android.graphics.Color;
import android.util.Log;

import com.example.OtakuCollect.framework.Game;
import com.example.OtakuCollect.framework.Graphics;
import com.example.OtakuCollect.framework.Screen;
import com.example.OtakuCollect.framework.Input.TouchEvent;

public class HighScoreRunkingScreen extends Screen {
	private String[][] list;

	public HighScoreRunkingScreen(Game game) {
		super(game);
		list = Utils.readFile(game.getFileIO());
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (isBounds(event, 270, 680, 200, 100)) {
					game.setScreen(new StartScreen(game));
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 481, 801, Color.WHITE);
		g.drawPixmap(Assets.image_play_buck, 0, 0);
		g.drawPixmap(Assets.image_RunkingScreen, 0, 0);
		g.drawPixmap(Assets.bt_title, 280, 700);
		if (list == null) {
			g.drawTextAlp("登録スコアがありません", 100, 250, Color.RED, 60);
		} else {
			for (int i = 0; i < list.length; i++) {
				for (int j = 0; j < list[0].length && list[i][j] != null; j++) {
					g.drawTextAlp(list[i][j], 150 + j * 190, (i + 1) * 230,
							Color.RED, 60);
				}
			}
		}
	}

	private boolean isBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
