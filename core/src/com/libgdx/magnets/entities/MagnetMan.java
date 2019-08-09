package com.libgdx.magnets.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.libgdx.magnets.Constants;
import com.libgdx.magnets.MagnetsGame;

public class MagnetMan extends Actor {

    Texture texture;
    Sprite sprite;


    public MagnetMan() {

        sprite = new Sprite(new Texture(Gdx.files.internal("magnet_small.png")));
        sprite.setPosition(Constants.GAME_WIDTH/2f,Constants.GAME_HEIGHT/2f);

//        texture = new Texture(Gdx.files.internal("test.png"));

    }

    @Override
    public void draw(Batch batch,float parentAlpha) {

        System.out.println("asdf");

        sprite.draw(batch);


    }

}
