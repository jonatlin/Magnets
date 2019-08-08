package com.libgdx.magnets.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.libgdx.magnets.MagnetsGame;
import com.libgdx.magnets.entities.MagnetMan;

public class GameScreen implements Screen {

    private final MagnetsGame GAME;

//    private final World world;
    private MagnetMan magnetMan;

    public GameScreen(final MagnetsGame game) {

        this.GAME = game;

//        world = new World(0, true);


        initObjects();


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        GAME.shapeRenderer.setProjectionMatrix(GAME.camera.combined);

        Gdx.gl.glClearColor(0.2f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        GAME.batch.begin();
//        GAME.font.draw(GAME.batch, "Welcome to Drop!!! ", 100, 150);
//        GAME.font.draw(GAME.batch, "Tap anywhere to begin!", 100, 100);
        GAME.batch.end();

        magnetMan.draw( GAME.shapeRenderer);

    }

    @Override
    public void resize(int width, int height) {

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
    }
}
