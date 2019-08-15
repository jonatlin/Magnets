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
import com.libgdx.magnets.GameManager;
import com.libgdx.magnets.Stages.HudStage;
import com.libgdx.magnets.MagnetsGame;
import com.libgdx.magnets.entities.Magnet;
import com.libgdx.magnets.entities.Power;
import com.libgdx.magnets.entities.Robot;
import com.libgdx.magnets.entities.WallBody;

public class GameScreen implements Screen {

    private final MagnetsGame GAME;
    private Stage stage;

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

    // timer
    Timer timer;
    TimeUtils a;


    public GameScreen(final MagnetsGame game) {

        this.GAME = game;
        this.stage = new Stage(GAME.viewport, GAME.batch);
        Gdx.input.setInputProcessor(stage);

        world = new World(new Vector2(0, 0), true);

        debugRenderer = new Box2DDebugRenderer();



        // outer wall sprite
        outerWallSprite = new Sprite((new Texture(Gdx.files.internal("outer_walls2.png"))));
        outerWallSprite.setPosition(0,0);
/*
        // Main character
        robot = new Robot(world, 30,30);

        // Magnets
        magnet1 = new Magnet(world, 40,40);
        magnet2 = new Magnet(world, 10,10);

        // Power
        power = new Power(20,40);*/

        gameManager = new GameManager();
        /*gameManager.addRobot(robot);
        gameManager.addMagnet(magnet1);
        gameManager.addMagnet(magnet2);
        gameManager.addPower(power);*/

        hudStage = new HudStage(game.batch, GAME.font, 60);
        gameManager.setHudStage(hudStage);
        gameManager.setWorld(world);

        gameManager.generateWalls();
        gameManager.generateNewLevel();



    }

    @Override
    public void show() {

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

            Vector2 pointClicked = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(),Gdx.input.getY()));
            
//            gameManager.updateMagnetState(Gdx.input.getX(), Gdx.input.getY());
            gameManager.updateMagnetState(pointClicked.x, pointClicked.y);

        }

        /*double distance = Math.sqrt( Math.pow(robot.getX() - magnet1.getX() , 2) + Math.pow((robot.getY() - magnet1.getY()),2));
        Vector2 repelVector = new Vector2(robot.getX() - magnet1.getX(), robot.getY() - magnet1.getY());
        Vector2 attractVector = new Vector2(magnet1.getX() - robot.getX(), magnet1.getY() - robot.getY());
        float scale = 12 * (float)(1/Math.pow(distance, 1.75));

        // magnetic force act
        if(magnet1.getState() == Magnet.State.REPEL)
            robot.b2body.applyLinearImpulse(repelVector.scl(scale), robot.b2body.getWorldCenter(), true);
        if(magnet1.getState() == Magnet.State.ATTRACT)
            robot.b2body.applyLinearImpulse(attractVector.scl(scale), robot.b2body.getWorldCenter(), true);*/


        world.step(1/60f,1,1);

        gameManager.act(delta);
        hudStage.update(delta);


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

        outerWallSprite.draw(GAME.batch);

        gameManager.draw(GAME.batch);

//        GAME.font.draw(GAME.batch, timeLayout, Gdx.graphics.getWidth()/2f - timeLayout.width/2f, Gdx.graphics.getHeight() - timeLayout.height - 1);
//        GAME.font.draw(GAME.batch, timeLayout,  Constants.GAME_WIDTH/2f - timeLayout.width/2f, Constants.GAME_HEIGHT - 3);
//        GAME.font.draw(GAME.batch, scoreLayout,  Constants.GAME_WIDTH - scoreLayout.width - 2, Constants.GAME_HEIGHT - 3);



        GAME.batch.end();

        GAME.batch.setProjectionMatrix(hudStage.stage.getCamera().combined);
        hudStage.stage.draw();

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
        stage.dispose();
        hudStage.dispose();


    }

}
