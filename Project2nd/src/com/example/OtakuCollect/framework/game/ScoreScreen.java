package com.example.OtakuCollect.framework.game;

import java.util.List;

import android.graphics.Color;
import android.util.Log;

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
					Log.d("EText", "'" + game.getEText() + "'");
					flag = Utils.addscore(game.getFileIO(), game.getEText(),
							score);
					return;
				}
				if (isBounds(event, 0, 700, 200, 100)) {
					game.chengeEditText(false);
					game.setScreen(new PlayScreen(game));
				}
				if (isBounds(event, 600, 700, 200, 100)) {
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
		g.drawPixmap(Assets.image_ScoreScreen, 0, 0);

		int count = 0;
		for (int _score = score; _score < 10; count++) {
			_score %= 10;
		}
		int score_x = 90-30*count;
		Log.d("count" +count, "x"+score_x);

		g.drawTextAlp("" + this.score, score_x, 370, Color.BLACK, 150);
		if (flag)
			g.drawTextAlp("登録完了", 110, 570, Color.RED, 70);
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
