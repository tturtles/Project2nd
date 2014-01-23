package com.example.OtakuCollect.framework.game;

import com.example.OtakuCollect.framework.Game;
import com.example.OtakuCollect.framework.Graphics;
import com.example.OtakuCollect.framework.Screen;
import com.example.OtakuCollect.framework.Graphics.PixmapFormat;
import com.example.OtakuCollect.framework.game.Utils;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
//		Assets.title = g.newPixmap("title.png", PixmapFormat.ARGB4444);
		Assets.bt_play = g.newPixmap("bt_play.png", PixmapFormat.ARGB4444);
		Assets.bt_score = g.newPixmap("bt_score.png", PixmapFormat.ARGB4444);
		Assets.bt_close = g.newPixmap("bt_close.png", PixmapFormat.ARGB4444);
		Assets.bt_retry = g.newPixmap("bt_retry.png", PixmapFormat.ARGB4444);
		Assets.bt_title = g.newPixmap("bt_title.png", PixmapFormat.ARGB4444);
		Assets.bt_left = g.newPixmap("bt_left.png", PixmapFormat.ARGB4444);
		Assets.bt_right = g.newPixmap("bt_right.png", PixmapFormat.ARGB4444);
		Assets.bt_touroku = g.newPixmap("bt_touroku.png", PixmapFormat.ARGB4444);
		Assets.image_otaku = g.newPixmap("image_otaku.png", PixmapFormat.ARGB4444);
		Assets.image_nototaku = g.newPixmap("image_nototaku.png", PixmapFormat.ARGB4444);
		Assets.image_doujinshi = g.newPixmap("image_doujinshi.png", PixmapFormat.ARGB4444);
		Assets.image_figure = g.newPixmap("image_figure.png", PixmapFormat.ARGB4444);
		Assets.image_tapestry = g.newPixmap("image_tapestry.png", PixmapFormat.ARGB4444);
		Assets.image_start_buck = g.newPixmap("image_start_buck.png", PixmapFormat.ARGB4444);
		Assets.image_play_buck = g.newPixmap("image_play_buck.png", PixmapFormat.ARGB4444);
		Assets.image_bl = g.newPixmap("image_bl.png", PixmapFormat.ARGB4444);
		Assets.logo_title = g.newPixmap("logo_title.png", PixmapFormat.ARGB4444);
		Utils.load(game.getFileIO());
		game.setScreen(new StartScreen(game));
	}

	@Override
	public void present(float deltaTime) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void pause() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void resume() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void dispose() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
