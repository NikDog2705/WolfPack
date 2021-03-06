package com.mygame.wolfpack.busov;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.wolfpack.busov.Screens.Level.level_1;
import com.mygame.wolfpack.busov.Screens.Levels;

public class Game extends com.badlogic.gdx.Game {
	public SpriteBatch spriteBatch;
	public BitmapFont bitmapFont;
	//public Music music;
	public OrthographicCamera camera;
	public static double ScreenWidht = 600, Re_ScreenWidht;
	public static double ScreenHeight = 950, Re_ScreenHeight;
	public static double GameScreenHeight = 900;
	public static double Re_widht;
	public static double Re_height;

	@Override
	public void create() {
		//music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		spriteBatch = new SpriteBatch();
		bitmapFont = new BitmapFont();

		//music.setLooping(true);
		//music.play();

		camera = new OrthographicCamera();
		//camera.setToOrtho(false,(int)ScreenWidht, (int)ScreenHeight);
		Re_widht = Gdx.graphics.getWidth();
		Re_height = Gdx.graphics.getHeight();
		if (Re_widht / Re_height < ScreenWidht / ScreenHeight) {
			Re_ScreenHeight = (Re_height / Re_widht) * ScreenWidht;
			Re_ScreenWidht = ScreenWidht;
		}
		else {
			Re_ScreenWidht = (Re_widht / Re_height) * ScreenHeight;
			Re_ScreenHeight = ScreenHeight;
		}
		camera.setToOrtho(false,(int)Re_ScreenWidht, (int)Re_ScreenHeight);
		bitmapFont.setColor(0,0,0,1);
		bitmapFont.getData().setScale(2);
		this.setScreen(new Levels(this));
	}

	@Override

	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		spriteBatch.dispose();
		bitmapFont.dispose();
	}
}
