package com.example.OtakuCollect.framework.game;

import java.util.List;

import android.graphics.Color;

import com.example.OtakuCollect.framework.Game;
import com.example.OtakuCollect.framework.Graphics;
import com.example.OtakuCollect.framework.Screen;
import com.example.OtakuCollect.framework.Input.TouchEvent;

public class HighScoreRunkingScreen extends Screen {


	public HighScoreRunkingScreen(Game game) {
		super(game);
		Utils.readFile(game.getFileIO());
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
		String[] names = Utils.getNames(game.getFileIO());
		String[] scores = Utils.getScores(game.getFileIO());
		g.drawRect(0, 0, 481, 801, Color.WHITE);
		g.drawPixmap(Assets.bt_title, 270, 680);

		for (int i = 0; i < Utils.getRecode(game.getFileIO()); i++) {
			g.drawTextAlp(names[i], 20, (i + 1) * 50, Color.RED, 20);
			g.drawTextAlp(scores[i], 100, (i + 1) * 50, Color.RED, 20);
		}
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
