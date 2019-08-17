package com.libgdx.magnets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdx.magnets.Stages.MainMenuStage;
import com.libgdx.magnets.screens.GameScreen;
import com.libgdx.magnets.screens.GameSummaryScreen;
import com.libgdx.magnets.screens.MainMenuScreen;

public class MagnetsGame extends Game {

	// resource generators
	public SpriteBatch batch;
	public BitmapFont font;

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


		font = new BitmapFont(Gdx.files.internal("fonts/small_px.fnt"));
		font.getData().setScale(1f,1f);


		batch.setProjectionMatrix(camera.combined);

//		setScreen(new GameScreen(this));

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
//		shapeRenderer.dispose();
		font.dispose();

		/*batch.dispose();
		img.dispose();*/
	}
}
