package com.example.OtakuCollect.framework.game;

import java.util.Random;

import com.example.OtakuCollect.framework.Graphics;
import com.example.OtakuCollect.framework.Pixmap;

public class Figure extends Sprite {

	private int vy;
	private int vx;

	private static final float TICK_INITIAL = 0.5f;
	private static float tick = TICK_INITIAL; // 更新速度
	private float tickTime;

	public Figure() {
		image = Assets.image_figure;
		width = 70;
		height = 70;
		Random rand = new Random();
		this.x = rand.nextInt(480-width);
		this.y = -height;
		speedY = 15;
	}

	public void Update() {
		vy = speedY;
		y += vy;
		vx = speedX;
		x += vx;
	}

	/*
	 * 以下getter,setter群
	 */
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
