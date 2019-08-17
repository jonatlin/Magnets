package com.libgdx.magnets.Stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdx.magnets.utility.Constants;
import com.libgdx.magnets.MagnetsGame;

public class HudStage extends Stage {

//    public Stage stage;
    private Viewport viewport;

    private ImageButton exitButton;

    private Label countdownLabel;
    private static Label scoreLabel;

    private int countdown;
    private int score;

    private int startCountdown;

    private Constants.GameMode mode;

    public HudStage(final MagnetsGame game, int countdown, Constants.GameMode mode) {

        super(game.viewport, game.batch);

        this.mode = mode;
//        this.game = game;

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Drawable exitDrawable = new TextureRegionDrawable(new Texture(Gdx.files.internal("buttons/exit_button_red.png")));
        exitButton = new ImageButton(exitDrawable);

        // shouldn't check here
        /*exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exiting");
                game.setScreen(new GameSummaryScreen(game, Constants.GameMode.FREE_PLAY, 1));
            }
        });*/

        table.add(exitButton).expandX().left();


        if(this.mode == Constants.GameMode.FREE_PLAY) {
            countdownLabel = null;
        }
        else {
            startCountdown = countdown;
            this.countdown = startCountdown;
            countdownLabel = new Label("Start", new Label.LabelStyle(game.font, Color.WHITE));
            table.add(countdownLabel).padTop(0).expandX().center();
        }

//        scoreLabel = new Label(String.format(Locale.US,"%04d", score), new Label.LabelStyle(game.font,Color.valueOf("ffc525")));
        scoreLabel = new Label(String.valueOf(score), new Label.LabelStyle(game.font,Color.valueOf("ffc525")));
        score = 0;
        table.add(scoreLabel).padTop(0).expandX().right().padRight(1);

//        this.addActor(exitButton);
        this.addActor(table);

    }

    public void setGameMode(Constants.GameMode mode) {
        this.mode = mode;
    }

    public ImageButton getExitButton() {
        return exitButton;
    }

    public void setTime(int time) {
        countdownLabel.setText(time);

    }

    public void setScore(int score) {
        this.score = score;
//        scoreLabel.setText(String.format(Locale.US,"%04d", score));
        scoreLabel.setText(String.valueOf(score));

    }

    public boolean isExitButtonArea(float x, float y) {
//        System.out.println("check exit button clicked");

        if(exitButton.getX() < x && (exitButton.getX() + exitButton.getWidth()) > x)
            if(exitButton.getY() < y && (exitButton.getY() + exitButton.getHeight()) > y)
                return true;
        return false;

        // should use actors, but exitButton not added to stage.
//        return (this.hit(x,y, true).equals( exitButton));


    }

    public boolean isCountdownDone() {
        return countdown<=0;
    }

}
