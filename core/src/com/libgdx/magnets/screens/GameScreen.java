package com.libgdx.magnets.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.libgdx.magnets.utility.Constants;
import com.libgdx.magnets.GameManager;
import com.libgdx.magnets.Stages.HudStage;
import com.libgdx.magnets.MagnetsGame;

public class GameScreen implements Screen {

    private final MagnetsGame GAME;
//    private Stage stage;

    private World world;
    private Box2DDebugRenderer debugRenderer;

    private GameManager gameManager;

    private HudStage hudStage;

    private Sprite outerWallSprite;

    // character speed
    private int v_x = 0;
    private int v_y = 0;

    // input pressed
    private boolean pressed;
    private boolean justPressed;


    private Music music;


    public GameScreen(final MagnetsGame game, Constants.GameMode mode) {

        this.GAME = game;
//        this.stage = new Stage(GAME.viewport, GAME.batch);

        world = new World(new Vector2(0, 0), true);

        debugRenderer = new Box2DDebugRenderer();

        // outer wall sprite
        outerWallSprite = new Sprite((new Texture(Gdx.files.internal("backgrounds/play_background1.png"))));
        outerWallSprite.setPosition(0,0);

        // init game manager, hud, box2d world
        gameManager = new GameManager();
        gameManager.setGameMode(mode);
        hudStage = new HudStage(GAME, 60, mode);
        gameManager.setHudStage(hudStage);
        gameManager.setWorld(world);
        gameManager.generateWalls();
        gameManager.generateNewLevel();

        // play background music
        music = GAME.manager.get("audio/bensound-perception.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(0.05f);
        music.play();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(hudStage);

    }

    public void update(float delta) {

        // arrow keys to move character
/*       pressed = false;

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
        v_y= 0;*/


//        System.out.println(justPressed);
        // detect mouse click, isButtonJustPressed not working for html
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if(!justPressed) {

                Vector2 pointClicked = hudStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

                gameManager.checkClick(pointClicked.x, pointClicked.y);

                if (hudStage.isExitButtonArea(pointClicked.x, pointClicked.y)) {
                    music.stop();
                    GAME.setScreen(new GameSummaryScreen(GAME, gameManager.getGameMode(), gameManager.getScore()));
                }
            }
            justPressed = true;

        } else {
            justPressed = false;
        }

        world.step(1/60f,1,1);

        gameManager.act(delta);

        if(gameManager.isCountdownDone()) {
            music.stop();
            GAME.setScreen( new GameSummaryScreen(GAME, gameManager.getGameMode(), gameManager.getScore()));
        }

    }

    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(0f, 0f, 0f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw objects
        GAME.batch.begin();

        gameManager.draw(GAME.batch);

        GAME.batch.end();

        GAME.batch.setProjectionMatrix(hudStage.getCamera().combined);
        hudStage.draw();

        // box2d debugging lines
//        debugRenderer.render(world, GAME.camera.combined);


    }

    @Override
    public void resize(int width, int height) {
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
        hudStage.dispose();


    }

}
