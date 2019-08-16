package com.libgdx.magnets.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdx.magnets.Constants;

import java.util.Locale;

public class HudStage implements Disposable {

    public Stage stage;
    private Viewport viewport;

    private Label countdownLabel;
    private static Label scoreLabel;

    private int countdown;
    private int score;
    private float timeCount;

    private int scoreIncrement = 1;

    private int startCountdown;

    private Constants.GameMode mode;

    /*public enum Mode {
        FREE_PLAY, STANDARD
    }*/

    public HudStage(SpriteBatch sb, BitmapFont font, Viewport viewport, int countdown, Constants.GameMode mode) {

        this.mode = mode;

        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format(Locale.US,"%03d", score), new Label.LabelStyle(font, Color.WHITE));
        score = 0;
        table.add(scoreLabel).expandX().padTop(0);


        if(mode == Constants.GameMode.FREE_PLAY) {
            countdownLabel = null;
        }
        else {
            startCountdown = countdown;
            this.countdown = startCountdown;
            countdownLabel = new Label(String.format(Locale.US,"%03d", countdown), new Label.LabelStyle(font, Color.CHARTREUSE));
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
