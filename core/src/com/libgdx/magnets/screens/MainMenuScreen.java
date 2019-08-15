package com.libgdx.magnets.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.libgdx.magnets.MagnetsGame;
import com.libgdx.magnets.Stages.MainMenuStage;

public class MainMenuScreen extends ScreenAdapter {

    MagnetsGame GAME;
    MainMenuStage stage;

    ImageButton zenButton;
    ImageButton.ImageButtonStyle buttonStyle;

    TextButton textButton;
    TextButton.TextButtonStyle textButtonStyle;

    Sprite mainMenuBackground;

    public MainMenuScreen(final MagnetsGame game) {

        this.GAME = game;
        this.stage = new MainMenuStage(GAME.viewport, GAME.batch);

        mainMenuBackground = new Sprite(new Texture(Gdx.files.internal("main_menu_background.png")));

        //Create Table
        Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        //Set alignment of contents in the table.
        mainTable.center();

//        buttonStyle = new ImageButton.ImageButtonStyle();
        Drawable drawable = new TextureRegionDrawable(new Texture(Gdx.files.internal("zen_button.png")));
        zenButton = new ImageButton(drawable);

        zenButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Starting Game");
                GAME.setScreen(new GameScreen(GAME));
            }
        });

        mainTable.add(zenButton);
        stage.addActor(mainTable);
//        stage.addActor(zenButton);


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        GAME.batch.begin();
        mainMenuBackground.draw(GAME.batch);
        GAME.batch.end();

        stage.act();
        stage.draw();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void resize(int width, int height) {
//        stage.getViewport().update(width, height, false);
        GAME.viewport.update(width, height, false);
        GAME.camera.update();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
