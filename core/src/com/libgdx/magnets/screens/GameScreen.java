package com.libgdx.magnets.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.libgdx.magnets.Constants;
import com.libgdx.magnets.GameManager;
import com.libgdx.magnets.Stages.HudStage;
import com.libgdx.magnets.MagnetsGame;
import com.libgdx.magnets.entities.Magnet;
import com.libgdx.magnets.entities.Power;
import com.libgdx.magnets.entities.Robot;
import com.libgdx.magnets.entities.WallBody;

public class GameScreen implements Screen {

    private final MagnetsGame GAME;
//    private Stage stage;

    private World world;
    private Box2DDebugRenderer debugRenderer;

    private GlyphLayout timeLayout;
    private GlyphLayout scoreLayout;

    private Robot robot;
    private Magnet magnet1, magnet2, magnet3;
    private Power power;

    private WallBody northWallBody,eastWallBody,southWallBody,westWallBody;

    private GameManager gameManager;

    private HudStage hudStage;

    private Sprite outerWallSprite;
    private Sprite northWallSprite, southWallSprite, eastWallSprite, westWallSprite;

    // character speed
    private int v_x = 0;
    private int v_y = 0;

    // input pressed
    private boolean pressed;


    public GameScreen(final MagnetsGame game, Constants.GameMode mode) {

        this.GAME = game;
//        this.stage = new Stage(GAME.viewport, GAME.batch);

        world = new World(new Vector2(0, 0), true);

        debugRenderer = new Box2DDebugRenderer();

        // outer wall sprite
        outerWallSprite = new Sprite((new Texture(Gdx.files.internal("backgrounds/play_background1.png"))));
        outerWallSprite.setPosition(0,0);

        gameManager = new GameManager();
        gameManager.setGameMode(mode);

        hudStage = new HudStage(GAME, 60, mode);
//        Gdx.input.setInputProcessor(hudStage);
        gameManager.setHudStage(hudStage);
        gameManager.setWorld(world);

        gameManager.generateWalls();
        gameManager.generateNewLevel();



    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(hudStage);

    }

    public void update(float delta) {
       pressed = false;

//        key press movement
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            v_x += 30;
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            v_x -= 30;
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            v_y += 30;
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            v_y -= 30;
            pressed = true;
        }
       // robot magnetism no movement
       if(pressed)
           gameManager.getRandomRobot().getBody().setLinearVelocity(v_x, v_y);

        v_x = 0;
        v_y= 0;

        // detect mouse click
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {

//            System.out.println(GAME.viewport.getScaling());

//            if(hudStage.hit(Gdx.input.getX(),Gdx.input.getY(), true).equals(hudStage.))

            Vector2 pointClicked = hudStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(),Gdx.input.getY()));

//            gameManager.updateMagnetState(Gdx.input.getX(), Gdx.input.getY());
            gameManager.updateMagnetClick(pointClicked.x, pointClicked.y);
//                gameManager.checkClick(pointClicked.x, pointClicked.y);

            if(hudStage.isExitButtonArea(pointClicked.x, pointClicked.y)) {
                GAME.setScreen( new GameSummaryScreen(GAME, gameManager.getGameMode(), gameManager.getScore()));
            }

        }

        world.step(1/60f,1,1);

        gameManager.act(delta);

        if(gameManager.isCountdownDone()) {
            GAME.setScreen( new GameSummaryScreen(GAME, gameManager.getGameMode(), gameManager.getScore()));
        }

    }

    @Override
    public void render(float delta) {

        update(delta);

//        GAME.shapeRenderer.setProjectionMatrix(GAME.camera.combined);

        Gdx.gl.glClearColor(0.2f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        GAME.batch.setProjectionMatrix(GAME.camera.combined);



        // draw objects
        GAME.batch.begin();

//        outerWallSprite.draw(GAME.batch);

        gameManager.draw(GAME.batch);

        GAME.batch.end();

        GAME.batch.setProjectionMatrix(hudStage.getCamera().combined);
        hudStage.draw();

        debugRenderer.render(world, GAME.camera.combined);


    }

    @Override
    public void resize(int width, int height) {
//        stage.getViewport().update(width, height, false);
        GAME.viewport.update(width, height, false);
        GAME.camera.update();
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

        world.dispose();
//        stage.dispose();
        hudStage.dispose();


    }

}
