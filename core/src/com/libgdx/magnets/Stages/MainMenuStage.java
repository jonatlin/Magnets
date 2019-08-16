package com.libgdx.magnets.Stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdx.magnets.Constants;
import com.libgdx.magnets.MagnetsGame;
import com.libgdx.magnets.screens.GameScreen;

public class MainMenuStage extends Stage {

    public Stage stage;
    private Viewport viewport;
    ImageButton zenButton;
    private final MagnetsGame GAME;


    public MainMenuStage(MagnetsGame game) {

        this.GAME = game;

        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, GAME.batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Drawable drawable = new TextureRegionDrawable(new Texture(Gdx.files.internal("robot/robot_big.png")));
        zenButton = new ImageButton(drawable);

        zenButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Starting Game");
                GAME.setScreen(new GameScreen(GAME, Constants.GameMode.STANDARD));
            }
        });

        table.add(zenButton).expandX().padTop(1);
        stage.addActor(table);

    }

    @Override
    public void dispose() { stage.dispose(); }

}
