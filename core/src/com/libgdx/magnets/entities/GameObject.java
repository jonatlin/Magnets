package com.libgdx.magnets.entities;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameObject extends Actor {

    protected float x;
    protected float y;


    public float getx() { return x; }
    public float gety() { return y; }


    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

}
