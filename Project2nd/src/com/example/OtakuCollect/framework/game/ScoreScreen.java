package com.example.OtakuCollect.framework.game;

import java.util.List;

import android.graphics.Color;

import com.example.OtakuCollect.framework.Game;
import com.example.OtakuCollect.framework.Graphics;
import com.example.OtakuCollect.framework.Screen;
import com.example.OtakuCollect.framework.Input.TouchEvent;
import com.example.OtakuCollect.framework.game.Utils;
import com.example.OtakuCollect.framework.game.Assets;

public class ScoreScreen extends Screen {

	private int score = 0;
	private boolean flag = false;

	public ScoreScreen(Game game, int score) {
		super(game);
		this.score = score;
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (isBounds(event, 140, 400, 200, 100)) {
					Utils.addscore(game.getFileIO(), game.getEText(), score);
					flag = true;
					return;
				}
				if (isBounds(event, 20, 650, 140, 100)) {
					game.chengeEditText(false);
					game.setScreen(new PlayScreen(game));
				}
				if (isBounds(event, 200, 650, 200, 100)) {
					game.setScreen(new StartScreen(game));
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
		g.drawRect(0, 0, 481, 801, Color.WHITE);
		g.drawTextAlp("スコア", 30, 100, Color.RED, 50);
		g.drawTextAlp("" + this.score, 200, 200, Color.BLACK, 100);
		g.drawPixmap(Assets.bt_retry, 20, 650);
		g.drawPixmap(Assets.bt_title, 200, 650);
		g.drawPixmap(Assets.bt_touroku, 140, 400);
		g.drawTextAlp("name", 30, 250, Color.RED, 50);
		// g.drawTextAlp("name", 0, 100, 30, 250, Color.RED, 50);
		g.drawPixmap(Assets.bt_touroku, 140, 400);
		if (flag)
			g.drawTextAlp("登録完了", 110, 470, Color.RED, 70);
		game.chengeEditText(true);
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
