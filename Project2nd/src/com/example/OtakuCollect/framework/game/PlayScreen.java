package com.example.OtakuCollect.framework.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.OtakuCollect.framework.Game;
import com.example.OtakuCollect.framework.Graphics;
import com.example.OtakuCollect.framework.Screen;
import com.example.OtakuCollect.framework.Input.TouchEvent;

public class PlayScreen extends Screen {

	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;

	private World world;
	private Otaku otaku;
	private int score = 0;

	public PlayScreen(Game game) {
		super(game);
		world = new World();
		otaku = new Otaku(215, 600);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		if (state == GameState.Ready)
			updateReady(touchEvents, deltaTime);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.GameOver) {
			updateGameOver(touchEvents);
		}
	}

	private void updateReady(List<TouchEvent> touchEvents, float deltaTime) {
		// ゲーム準備時のタッチ処理書き込み
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			switch (event.type) {
			case MotionEvent.ACTION_UP:
				state = GameState.Running;
			}
		}
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		// ゲーム中のタッチ処理書き込み
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			switch (event.type) {
			case MotionEvent.ACTION_MOVE:
			case MotionEvent.ACTION_DOWN:
				if (isBounds(event, 10, 730, 200, 60)) {
					// （RIGHT処理）
					otaku.accelerateLeft();
				}
				if (isBounds(event, 270, 730, 200, 60)) {
					// （LEFT処理）
					otaku.accelerateRight();
				}
				break;
			case MotionEvent.ACTION_UP:
				otaku.Cancel();
				break;
			}
		}
		otaku.Update(deltaTime);
		world.update(deltaTime);
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		// ゲームオーバー時のタッチ処理書き込み
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			switch (event.type) {
			case MotionEvent.ACTION_UP:
				game.setScreen(new ScoreScreen(game, score));
				return;
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.GameOver)
			drawGameOverUI();
	}

	private void drawReadyUI() {
		// ゲーム準備時のUI(描画系)
		Graphics g = game.getGraphics();
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(100);
		drawRunningUI();
		g.drawPixmap(Assets.logo_ready, 30, 200);
		paint.setTextSize(30);
		g.drawTextAlp("Tap on Start", 150, 500, paint);
	}

	private void drawRunningUI() {
		// ゲーム中のUI(描画系)
		Graphics g = game.getGraphics();
		Rect src = new Rect(0, 0, 480, 800);
		Rect dst = new Rect(0, 0, 480, 725);
		g.drawPixmap(Assets.image_play_buck, src, dst);
		world.draw(g);
		otaku.draw(g);
		LinkedList sprites = world.getSprites();
		Iterator iterator = sprites.iterator(); // Iterator=コレクション内の要素を順番に取り出す方法
		if (world.isFlag() && sprites.size() == 0)
			state = GameState.GameOver; // 終了判定
		while (iterator.hasNext()) { // iteratorの中で次の要素がある限りtrue
			Sprite sprite = (Sprite) iterator.next();
			sprite.Update();
			if (sprite.getY() > 725) {
				sprites.remove(sprite);
				break;
			}
			if (otaku.isCollision(sprite) && !otaku.getflag()) { // 衝突した場合
				if (sprite instanceof Doujinshi && !otaku.getflag())
					score += 30;
				if (sprite instanceof Figure && !otaku.getflag())
					score += 50;
				if (sprite instanceof Tapestry && !otaku.getflag())
					score += 70;
				if (sprite instanceof Bl && !otaku.getflag())
					otaku.setFlag();
				sprites.remove(sprite);
				break;
			}
		}
		
		iterator = sprites.iterator();
		while (iterator.hasNext()) {
			Sprite sprite = (Sprite) iterator.next();
			sprite.draw(g);
		}
		
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(50);
		int _score = score;
		int i;
		for (i = 0; _score > 9; i++) {
			if (_score / 10 < 10)
				break;
			else {
				i++;
				_score /= 10;
			}
		}
		g.drawTextAlp("SCORE : " + score, 200 - i * 15, 50, paint);
		g.drawRect(0, 725, 481, 800, Color.WHITE);
		g.drawLine(0, 725, 480, 725, Color.BLACK, 2);
		g.drawPixmap(Assets.bt_left, 10, 730);
		g.drawPixmap(Assets.bt_right, 270, 730);
	}

	private void drawGameOverUI() {
		// ゲームオーバー時のUI(描画系)
		Graphics g = game.getGraphics();
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(100);
		g.drawPixmap(Assets.logo_gameover, 30, 200);
	}

	// タップ時の当たり判定 目標がタップされた場合true、違う場合false
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
