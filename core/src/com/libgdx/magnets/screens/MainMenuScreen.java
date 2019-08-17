package com.libgdx.magnets.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.libgdx.magnets.utility.Constants;
import com.libgdx.magnets.MagnetsGame;

public class MainMenuScreen extends ScreenAdapter {

    private MagnetsGame GAME;
    private Stage stage;

    private Image logoImage;
    /*private ImageButton freePlayButton;
    private ImageButton standardButton;*/

    private TextField logoText;
    private TextField.TextFieldStyle textFieldStyle;

    private TextButton logoButton;
    private TextButton freePlayButton;
    private TextButton standardButton;
    private TextButton.TextButtonStyle textButtonStyle;




    private Sprite mainMenuBackground;

    public MainMenuScreen(final MagnetsGame game) {

        this.GAME = game;
        this.stage = new Stage();
        stage.setViewport(GAME.viewport);

        mainMenuBackground = new Sprite(new Texture(Gdx.files.internal("backgrounds/main_menu_background.png")));

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.align(Align.center);

        //Set alignment of contents in the table.
        mainTable.center();

       /* textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = game.font;
        textFieldStyle.fontColor = Color.BLACK;
        logoText = new TextField("MAGNETS", textFieldStyle);

        logoButton = new TextButton("STANDARD", textButtonStyle);
        logoButton.scaleBy(2);*/

        // entity drawables
        Drawable buttonDrawable = new TextureRegionDrawable(new Texture(Gdx.files.internal("buttons/blank_button.png")));
        Drawable logoDrawable = new TextureRegionDrawable(new Texture(Gdx.files.internal("buttons/logo_text.png")));

        textButtonStyle = new TextButton.TextButtonStyle(buttonDrawable, buttonDrawable,buttonDrawable, GAME.font);

        logoImage = new Image(logoDrawable);
        standardButton = new TextButton("STANDARD", textButtonStyle);
        freePlayButton = new TextButton("FREE PLAY", textButtonStyle);


        standardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Starting Game");
                GAME.setScreen(new GameScreen(GAME,  Constants.GameMode.STANDARD));
            }
        });

        freePlayButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Starting Game");
                GAME.setScreen(new GameScreen(GAME, Constants.GameMode.FREE_PLAY));
            }
        });

        mainTable.add(logoImage).padBottom(5);
        mainTable.row();
        mainTable.add(standardButton).padTop(2);
        mainTable.row();
        mainTable.add(freePlayButton).padTop(2);

        stage.addActor(mainTable);
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
        GAME.viewport.update(width, height, false);
        GAME.camera.update();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
