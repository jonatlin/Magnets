package com.libgdx.magnets.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Power extends Sprite {

    public int characterWidth = 4;
    public int characterHeight = 9;

    public Power(int pos_x, int pos_y) {

        super((new Texture(Gdx.files.internal("power.png"))));

        setBounds(pos_x, pos_y,characterWidth,characterHeight);

    }


}
