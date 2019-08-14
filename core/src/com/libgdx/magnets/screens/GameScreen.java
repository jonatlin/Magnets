package com.libgdx.magnets.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.libgdx.magnets.Constants;
import com.libgdx.magnets.MagnetsGame;
import com.libgdx.magnets.entities.Magnet;
import com.libgdx.magnets.entities.MagnetMan;
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
    private WallBody northWallBody,eastWallBody,southWallBody,westWallBody;

    private Sprite outerWallSprite;
    private Sprite northWallSprite, southWallSprite, eastWallSprite, westWallSprite;

    // character speed
    private int v_x = 0;
    private int v_y = 0;

    // input pressed
    private boolean pressed;

    public GameScreen(final MagnetsGame game) {

        this.GAME = game;
        /*this.stage = new Stage(GAME.viewport, GAME.batch);
        Gdx.input.setInputProcessor(stage);
*/
        world = new World(new Vector2(0, 0), true);

        debugRenderer = new Box2DDebugRenderer();

        // map boundaries
        northWallBody = new WallBody(world, 1,63,63,63);
        eastWallBody = new WallBody(world, 63,1,63,63);
        southWallBody = new WallBody(world, 1,1,63,1);
        westWallBody = new WallBody(world, 1,1,1,63);

        // outer wall sprite
        outerWallSprite = new Sprite((new Texture(Gdx.files.internal("outer_walls2.png"))));
        outerWallSprite.setPosition(0,0);

        // Main character
        robot = new Robot(world, 30,30);

        // Magnets
        magnet1 = new Magnet(world, 40,40);

        // font
        timeLayout = new GlyphLayout(GAME.font, "100");
        GAME.font.setColor(Color.RED);
        scoreLayout = new GlyphLayout(GAME.font, "024");
//        System.out.println(scoreLayout.height);



        // wall sprites
       /* northWallSprite = new Sprite((new Texture(Gdx.files.internal("wall.png"))));
        northWallSprite.setPosition(0, 64);
        northWallSprite.setOrigin(0, 0);
        northWallSprite.rotate(270);

        eastWallSprite = new Sprite((new Texture(Gdx.files.internal("wall.png"))));
        eastWallSprite.setPosition(Constants.GAME_WIDTH-1, 0);


        southWallSprite = new Sprite((new Texture(Gdx.files.internal("wall.png"))));
        southWallSprite.setPosition(0, 10);
        southWallSprite.setOrigin(0,0);
        southWallSprite.rotate(270);

        westWallSprite = new Sprite((new Texture(Gdx.files.internal("wall.png"))));
        westWallSprite.setPosition(0, 0);*/


//        s = new Sprite((new Texture(Gdx.files.internal("MagnetMan.png"))));

      /*  Texture splash = new Texture(Gdx.files.internal("battle-arena-background.png"));
        image = new Image(splash);
        image.setPosition(0,0);



//        stage.addActor(image);
        stage.addActor(magnetMan);
        stage.addActor(magnet);

        stage.setKeyboardFocus(magnetMan);*/

//        world = new World(0, true);




    }

    @Override
    public void show() {

    }

    public void update(float delta) {
       /* if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && robot.b2body.getLinearVelocity().x <= 2)//
//            robot.b2body.setLinearVelocity(15,0);
                        robot.b2body.applyLinearImpulse(new Vector2(2f, 0), robot.b2body.getWorldCenter(), true);
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && robot.b2body.getLinearVelocity().x >= -2)
            robot.b2body.applyLinearImpulse(new Vector2(-2f, 0), robot.b2body.getWorldCenter(), true);
        else if (Gdx.input.isKeyPressed(Input.Keys.UP) && robot.b2body.getLinearVelocity().x <= 2)
            robot.b2body.applyLinearImpulse(new Vector2(0, 2f), robot.b2body.getWorldCenter(), true);
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && robot.b2body.getLinearVelocity().x >= -2)
            robot.b2body.applyLinearImpulse(new Vector2(0, -2f), robot.b2body.getWorldCenter(), true);
        else
            robot.b2body.setLinearVelocity(0,0);*/
       pressed = false;

//        key press movement
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            robot.b2body.setLinearVelocity(30, robot.b2body.getLinearVelocity().y);
            v_x += 30;
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            robot.b2body.setLinearVelocity(-30, robot.b2body.getLinearVelocity().y);
            v_x -= 30;
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            robot.b2body.setLinearVelocity(robot.b2body.getLinearVelocity().x, 30);
            v_y += 30;
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            robot.b2body.setLinearVelocity(robot.b2body.getLinearVelocity().x, -30);
            v_y -= 30;
            pressed = true;
        }
       /* if(!pressed)
            robot.b2body.setLinearVelocity(0,0);
        robot.b2body.setLinearVelocity(v_x, v_y);*/

       // robot magnetism no movement
       if(pressed)
           robot.b2body.setLinearVelocity(v_x, v_y);

        v_x =0;
        v_y=0;

        // detect mouse click

        // USE input processor
        // https://github.com/libgdx/libgdx/wiki/Event-handling
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            magnet1.cycleState();
            System.out.println(magnet1.getState());
        }

            // attract
//        robot.b2body.applyLinearImpulse(new Vector2(magnet1.getX() - robot.getX(), magnet1.getY() - robot.getY()), robot.b2body.getWorldCenter(), true);

        double distance = Math.sqrt( Math.pow(robot.getX() - magnet1.getX() , 2) + Math.pow((robot.getY() - magnet1.getY()),2));
        Vector2 repelVector = new Vector2(robot.getX() - magnet1.getX(), robot.getY() - magnet1.getY());
        Vector2 attractVector = new Vector2(magnet1.getX() - robot.getX(), magnet1.getY() - robot.getY());
        float scale = 12 * (float)(1/Math.pow(distance, 1.75));

        // magnetic force act
        if(magnet1.getState() == Magnet.State.REPEL)
            robot.b2body.applyLinearImpulse(repelVector.scl(scale), robot.b2body.getWorldCenter(), true);
        if(magnet1.getState() == Magnet.State.ATTRACT)
            robot.b2body.applyLinearImpulse(attractVector.scl(scale), robot.b2body.getWorldCenter(), true);


//        System.out.println(scale);
//        System.out.println(robot.b2body.getWorldCenter());

        world.step(1/60f,1,1);
        robot.update(delta);

//        eastWallBody.update(delta);
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
        robot.draw(GAME.batch);

        magnet1.draw(GAME.batch);

//        timeLayout.width = 3;

//        GAME.font.draw(GAME.batch, timeLayout, Gdx.graphics.getWidth()/2f - timeLayout.width/2f, Gdx.graphics.getHeight() - timeLayout.height - 1);
        GAME.font.draw(GAME.batch, timeLayout,  Constants.GAME_WIDTH/2f - timeLayout.width/2f, Constants.GAME_HEIGHT - 3);
        GAME.font.draw(GAME.batch, scoreLayout,  Constants.GAME_WIDTH - scoreLayout.width - 2, Constants.GAME_HEIGHT - 3);

        GAME.batch.end();


        debugRenderer.render(world, GAME.camera.combined);

//        stage.act(delta);
//
//        stage.draw();


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


    }

}
