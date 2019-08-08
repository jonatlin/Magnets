package com.libgdx.magnets.entities;

public class GameObject {

    protected float x;
    protected float y;


    public float getx() { return x; }
    public float gety() { return y; }


    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

}
