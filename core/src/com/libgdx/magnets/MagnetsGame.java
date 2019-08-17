package com.libgdx.magnets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.libgdx.magnets.screens.MainMenuScreen;
import com.libgdx.magnets.utility.Constants;

public class MagnetsGame extends Game {

	// resource generators
	public SpriteBatch batch;
	public BitmapFont font;

	// camera
	public OrthographicCamera camera;
	public FitViewport viewport;

	public AssetManager manager;

	@Override
	public void create () {

		camera = new OrthographicCamera(Constants.GAME_WIDTH,Constants.GAME_HEIGHT);
		camera.translate(Constants.GAME_WIDTH/2f,Constants.GAME_HEIGHT/2f);
		camera.update();
		viewport = new FitViewport(Constants.GAME_WIDTH,Constants.GAME_HEIGHT,camera);
		viewport.apply();

		batch = new SpriteBatch();

		font = new BitmapFont(Gdx.files.internal("fonts/small_px.fnt"));
		font.getData().setScale(1f,1f);

		// prepare assets
		manager = new AssetManager();
		manager.load("audio/bensound-perception.mp3", Music.class);
		manager.finishLoading();

		batch.setProjectionMatrix(camera.combined);

		setScreen(new MainMenuScreen(this));

	}

	@Override
	public void render () {

		super.render();

	}
	
	@Override
	public void dispose () {
		super.dispose();

		batch.dispose();
		manager.dispose();
		font.dispose();

	}
}
