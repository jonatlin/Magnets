package com.libgdx.magnets.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Magnet extends Sprite {

    public enum State {
        REPEL, ATTRACT, OFF
    }

    public int width = 4;
    public int height = 4;

    public Body body;
    public World world;

    public State state;


    // pos_x, pos_y bottom left corner of sprite
    public Magnet(World world, int pos_x, int pos_y) {

        // set sprite based on state
        super((new Texture(Gdx.files.internal("entities/magnet/magnet_off.png"))));

//        this.width = width;
//        this.height = height;

        setState(State.OFF);
//        state = State.OFF;

        this.world = world;

        BodyDef bdef = new BodyDef();
        bdef.position.set(pos_x, pos_y);

        bdef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(width/2f, height/2f);

        fdef.shape = bodyShape;
        body.createFixture(fdef);

        setBounds(Math.round(body.getPosition().x - getWidth() / 2),Math.round(body.getPosition().y - getHeight() / 2),width,height);


    }
    
/*
    public static int getSize() {
        return Math.max(width, height);
    }
*/

    public void setState(State state) {
        this.state = state;
        updateSprite();
    }

    public void updateSprite() {
        if(state == State.ATTRACT)
            setRegion(new Texture(Gdx.files.internal("entities/magnet/magnet_attract.png")));
        if(state == State.REPEL)
            setRegion(new Texture(Gdx.files.internal("entities/magnet/magnet_repel.png")));
        if(state == State.OFF)
            setRegion(new Texture(Gdx.files.internal("entities/magnet/magnet_off.png")));
    }

    public State getState() {
        return this.state;
    }

    public Body getBody() {
        return this.body;
    }

    public void cycleState() {
        if(this.state == State.OFF) {
            setState(State.ATTRACT);
        }
        else if(this.state == State.ATTRACT) {
            setState(State.REPEL);
        }
        else if(this.state == State.REPEL) {
            setState(State.OFF);
        }
    }

}


