package com.libgdx.magnets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdx.magnets.screens.GameScreen;

public class MagnetsGame extends Game {

	// dimensions
	public static int WIDTH;
	public static int HEIGHT;

	// resource generators
	public SpriteBatch batch;
	public BitmapFont font;
	public ShapeRenderer shapeRenderer;

	// camera
	public OrthographicCamera camera;
	public FitViewport viewport;


	@Override
	public void create () {

		camera = new OrthographicCamera(Constants.GAME_WIDTH,Constants.GAME_HEIGHT);
		camera.translate(Constants.GAME_WIDTH/2f,Constants.GAME_HEIGHT/2f);
		camera.update();
		viewport = new FitViewport(Constants.GAME_WIDTH,Constants.GAME_HEIGHT,camera);
		viewport.apply();


		batch = new SpriteBatch();
		font = new BitmapFont();
		shapeRenderer = new ShapeRenderer();

		/*WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();

		WIDTH = 64;
		HEIGHT = 64;*/

//		System.out.println(WIDTH + String.valueOf(HEIGHT));

//		camera.setToOrtho(false, WIDTH, HEIGHT);
//		camera.update();


		setScreen(new GameScreen(this));

	}

	@Override
	public void render () {

		super.render();

	}
	
	@Override
	public void dispose () {
		super.dispose();

		batch.dispose();
		shapeRenderer.dispose();
		font.dispose();

		/*batch.dispose();
		img.dispose();*/
	}
}
