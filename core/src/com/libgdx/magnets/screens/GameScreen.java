package com.libgdx.magnets.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.libgdx.magnets.MagnetsGame;
import com.libgdx.magnets.entities.Magnet;
import com.libgdx.magnets.entities.MagnetMan;

public class GameScreen implements Screen {

    private final MagnetsGame GAME;
    private Stage stage;

//    private final World world;
    private MagnetMan magnetMan;
    private Magnet magnet;

    private Image image;

    public GameScreen(final MagnetsGame game) {

        this.GAME = game;
        this.stage = new Stage(GAME.viewport, GAME.batch);
        Gdx.input.setInputProcessor(stage);

        Texture splash = new Texture(Gdx.files.internal("battle-arena-background.png"));
        image = new Image(splash);
        image.setPosition(0,0);

        initObjects();


//        stage.addActor(image);
        stage.addActor(magnetMan);
        stage.addActor(magnet);

        stage.setKeyboardFocus(magnetMan);

//        world = new World(0, true);




    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        GAME.shapeRenderer.setProjectionMatrix(GAME.camera.combined);

        Gdx.gl.glClearColor(0.2f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        GAME.font.draw(GAME.batch, "Welcome to Drop!!! ", 100, 150);
//        GAME.font.draw(GAME.batch, "Tap anywhere to begin!", 100, 100);


        stage.act(delta);

        stage.draw();

       /* GAME.batch.begin();
        GAME.batch.end();*/


//        magnetMan.draw( GAME.shapeRenderer);


    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void initObjects() {
        magnetMan = new MagnetMan();
        magnet = new Magnet();
    }
}
