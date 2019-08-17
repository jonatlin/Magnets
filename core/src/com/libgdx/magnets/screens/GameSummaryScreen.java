package com.libgdx.magnets.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.libgdx.magnets.utility.Constants;
import com.libgdx.magnets.MagnetsGame;

public class GameSummaryScreen extends ScreenAdapter {

    private MagnetsGame GAME;
    private Stage stage;

    private Label gameModeLabel;
    private Label scoreLabel;

    private TextButton mainMenuButton;
    private TextButton.TextButtonStyle textButtonStyle;

    private Sprite gameSummaryBackground;

    public GameSummaryScreen(final MagnetsGame game, Constants.GameMode mode, int score) {

        this.stage = new Stage();
        this.GAME = game;

        stage.setViewport(GAME.viewport);

        gameSummaryBackground = new Sprite(new Texture(Gdx.files.internal("backgrounds/menu_background_horizontal.png")));

        // table to hold score/buttons
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.align(Align.center);

        // textfield/textarea doesn't display?
        /*textFieldStyle = new TextField.TextFieldStyle(game.font,  Color.BLACK, mainMenuButtonDrawable,mainMenuButtonDrawable,mainMenuButtonDrawable);
        textFieldStyle.font = game.font;
        textFieldStyle.fontColor = Color.BLACK;
        scoreTextArea = new TextArea("MAGNETS", textFieldStyle);*/

        String modeString = "STANDARD";

        //determine what mode was played
        if(mode == Constants.GameMode.STANDARD)
            modeString = "STANDARD";
        else if(mode == Constants.GameMode.FREE_PLAY)
            modeString = "FREE PLAY";

        // can't use String.format for html?
        gameModeLabel = new Label((modeString), new Label.LabelStyle(game.font, Color.WHITE));
//        scoreLabel = new Label(String.format(Locale.US,"%04d", score), new Label.LabelStyle(game.font, Color.valueOf("ffc525")));
        scoreLabel = new Label(score + "", new Label.LabelStyle(game.font, Color.valueOf("ffc525")));
        Gdx.app.log("MyTag", "" + score);

        // create back to main menu button style
        Drawable mainMenuButtonDrawable = new TextureRegionDrawable(new Texture(Gdx.files.internal("buttons/red_button.png")));
        textButtonStyle = new TextButton.TextButtonStyle(mainMenuButtonDrawable, mainMenuButtonDrawable,mainMenuButtonDrawable, GAME.font);

        mainMenuButton = new TextButton("MAIN MENU", textButtonStyle);
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Switching to Main Menu");
                GAME.setScreen(new MainMenuScreen(GAME));
            }
        });

        // add elements to table
        mainTable.add(gameModeLabel);
        mainTable.row();
        mainTable.add(scoreLabel).padBottom(10);
        mainTable.row();
        mainTable.add(mainMenuButton);

        stage.addActor(mainTable);



    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        GAME.batch.begin();
        gameSummaryBackground.draw(GAME.batch);
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
