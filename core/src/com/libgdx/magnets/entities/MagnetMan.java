package com.libgdx.magnets.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.libgdx.magnets.Constants;
import com.libgdx.magnets.MagnetsGame;

public class MagnetMan extends Actor {


    InputListener inputListener;
    Sprite sprite;


    public MagnetMan() {

        sprite = new Sprite(new Texture(Gdx.files.internal("MagnetMan.png")));
        this.setPosition(Constants.GAME_WIDTH/2f,Constants.GAME_HEIGHT/2f);

//        sprite.setPosition(Constants.GAME_WIDTH/2f,Constants.GAME_HEIGHT/2f);
//        sprite.setX(22);


        inputListener = new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {

                System.out.println("keydown");

                if(keycode == Input.Keys.RIGHT){

                    System.out.println(getX());

                    setPosition(getX() + 1, getY());

                    System.out.println("right");

                }

                if(keycode == Input.Keys.LEFT){

                    System.out.println(getX());

                    setPosition(getX() - 1, getY());

                    System.out.println("left");

                }

                if(keycode == Input.Keys.UP){

                    System.out.println(getX());

                    setPosition(getX(), getY() + 1);

                    System.out.println("up");

                }

                if(keycode == Input.Keys.DOWN){

                    System.out.println(getX());

                    setPosition(getX() , getY() - 1);

                    System.out.println("down");

                }
                return true;
            }
        };

        addListener(inputListener);

//        texture = new Texture(Gdx.files.internal("test.png"));

    }

    @Override
    public void draw(Batch batch,float parentAlpha) {

//        System.out.println("magnetman");

        sprite.draw(batch);


    }

    @Override
    public void setX(float x) {

        super.setX(x);
        sprite.setX(x);

    }

    @Override
    public void setY(float y) {

        super.setY(y);
        sprite.setY(y);

    }

    @Override
    public void setPosition(float x, float y) {

        super.setPosition(x,y);
        sprite.setPosition(x,y);

    }

}
