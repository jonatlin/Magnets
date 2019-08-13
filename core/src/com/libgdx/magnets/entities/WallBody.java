package com.libgdx.magnets.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class WallBody {

    public int characterWidth = 1;
    public int characterHeight = 64;

    public Body b2body;
    public World world;

    private EdgeShape wall;

    public WallBody(World world, int x1, int y1, int x2, int y2) {

        this.world = world;

        BodyDef bdef = new BodyDef();
        bdef.position.set(0, 0);

        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

        wall = new EdgeShape();
        wall.set(x1, y1, x2, y2);

        fdef.shape = wall;
        fdef.friction = 1;
        b2body.createFixture(fdef);

//        setBounds((x1 + x2)/2f, (y1+y2)/2f, characterWidth, characterHeight);

    }

    public void update(float dt) {
//        System.out.println(b2body.getPosition().x + ", " + b2body.getPosition().y);
    }
}
