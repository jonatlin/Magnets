package com.libgdx.magnets.Stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdx.magnets.Constants;
import com.libgdx.magnets.MagnetsGame;
import com.libgdx.magnets.screens.GameScreen;
import com.libgdx.magnets.screens.MainMenuScreen;

import java.util.Locale;

public class HudStage extends Stage {

    public Stage stage;
    private Viewport viewport;

    private ImageButton exitButton;

    private Label countdownLabel;
    private static Label scoreLabel;

    private int countdown;
    private int score;
    private float timeCount;

    private int scoreIncrement = 1;

    private int startCountdown;

    private Constants.GameMode mode;

//    private final MagnetsGame game;

    /*public enum Mode {
        FREE_PLAY, STANDARD
    }*/

    public HudStage(final MagnetsGame game, int countdown, Constants.GameMode mode) {

        super(game.viewport, game.batch);

        this.mode = mode;
//        this.game = game;

        stage = new Stage(game.viewport, game.batch);
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Drawable exitDrawable = new TextureRegionDrawable(new Texture(Gdx.files.internal("buttons/exit_button_filled.png")));
        exitButton = new ImageButton(exitDrawable);


        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Starting Game");
                game.setScreen(new MainMenuScreen(game));
            }
        });
        table.add(exitButton);

        scoreLabel = new Label(String.format(Locale.US,"%03d", score), new Label.LabelStyle(game.font, Color.WHITE));
        score = 0;
        table.add(scoreLabel).expandX().padTop(0);


        if(mode == Constants.GameMode.FREE_PLAY) {
            countdownLabel = null;
        }
        else {
            startCountdown = countdown;
            this.countdown = startCountdown;
            countdownLabel = new Label(String.format(Locale.US,"%03d", countdown), new Label.LabelStyle(game.font, Color.CHARTREUSE));
            table.add(countdownLabel).expandX().padTop(0);
        }
        stage.addActor(table);

    }

    public void update(float delta) {
        timeCount += delta;
        if(timeCount >= 1){
            if (countdown > 0) {
                countdown--;
            }
            if(this.mode!=Constants.GameMode.FREE_PLAY)
                countdownLabel.setText(String.format(Locale.US,"%03d", countdown));
            timeCount = 0;
        }
    }

    public void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format(Locale.US,"%03d", score));
    }

    public void addScore() {
        addScore(scoreIncrement);
    }

    public void setScore(int score) {
        this.score = score;
        scoreLabel.setText(String.format(Locale.US,"%03d", score));

    }

    public void reset() {
        score = 0;
        countdown = startCountdown;
    }

    @Override
    public void dispose() { stage.dispose(); }

    public boolean isCountdownDone() {
        return countdown<=0;
    }

}
