package com.example.OtakuCollect.framework.game;

import java.util.List;

import android.graphics.Color;

import com.example.OtakuCollect.framework.Game;
import com.example.OtakuCollect.framework.Graphics;
import com.example.OtakuCollect.framework.Screen;
import com.example.OtakuCollect.framework.Input.TouchEvent;
import com.example.OtakuCollect.framework.game.HighScoreRunkingScreen;

public class StartScreen extends Screen {

	public StartScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (isBounds(event, 80, 450, 320, 100)) {
					game.setScreen(new PlayScreen(game));
				} else if(isBounds(event, 80, 550, 320, 100)) {
					game.setScreen(new HighScoreRunkingScreen(game));
				} else if(isBounds(event, 80, 650, 320, 100)) {
					System.exit(0);
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
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 480, 800, Color.BLACK);
		g.drawPixmap(Assets.image_start_buck, 0, 0);
		g.drawPixmap(Assets.bt_play, 80, 450);
		g.drawPixmap(Assets.bt_score, 80, 550);
		g.drawPixmap(Assets.bt_close, 80, 650);
		game.chengeEditText(false);
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
