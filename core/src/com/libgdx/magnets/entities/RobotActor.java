package com.libgdx.magnets.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.libgdx.magnets.Constants;

public class RobotActor extends Actor {

    Sprite sprite;

    public int characterWidth = 5;
    public int characterHeight = 5;

    public Body b2body;
    public World world;


    public RobotActor(World world, int pos_x, int pos_y) {

        sprite = new Sprite(new Texture(Gdx.files.internal("robot_robot_blue.png")));
        this.setPosition(Constants.GAME_WIDTH/2f,Constants.GAME_HEIGHT/2f);

        this.world = world;

        BodyDef bdef = new BodyDef();
        bdef.position.set(pos_x, pos_y);

        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

//        CircleShape shape = new CircleShape();
//        shape.setRadius(2);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(characterWidth/2f, characterHeight/2f);

        fdef.shape = bodyShape;
        b2body.createFixture(fdef);
        b2body.setLinearDamping(5f);

//        b2body.createFixture(fdef).setUserData(this);

        setBounds(Math.round(b2body.getPosition().x - getWidth() / 2),Math.round(b2body.getPosition().y - getHeight() / 2),characterWidth,characterHeight);


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

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(sprite,getX(),getY());
    }


}
