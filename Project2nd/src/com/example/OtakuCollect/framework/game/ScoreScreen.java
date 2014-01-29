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
				if (isBounds(event, 90, 580, 300, 80)) {
					Log.d("EText", "'" + game.getEText() + "'");
					flag = Utils.addscore(game.getFileIO(), game.getEText(),
							score);
					return;
				}
				if (isBounds(event, 0, 700, 200, 100)) {
					game.chengeEditText(false);
					game.setScreen(new PlayScreen(game));
				}
				if (isBounds(event, 280, 700, 200, 100)) {
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
		g.drawPixmap(Assets.logo_score, 20, 40);
		g.drawPixmap(Assets.bt_retry, 0, 700);
		g.drawPixmap(Assets.bt_title, 280, 700);
		g.drawPixmap(Assets.image_otaku, 350,300, 0, 0, 100, 100);
		int count = 0;
		for (int _score = score; _score > 10; count++) {
			_score = _score / 10;
		}
		int score_x = 90 - 40 * count;
		g.drawTextAlp("" + this.score, score_x, 370, Color.BLACK, 150);
		if (flag)
			g.drawPixmap(Assets.bt_kanryou, 90, 580);
		else
			g.drawPixmap(Assets.bt_touroku, 90, 580);
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
