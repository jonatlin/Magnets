package com.libgdx.magnets.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Test extends Sprite {

    public Body b2body;
    public World world;

    public Test(World world) {

        super((new Texture(Gdx.files.internal("MagnetMan.png"))));
        // for set texture manually
//        setRegion(new Texture(Gdx.files.internal("MagnetMan.png")));

        this.world = world;

//        Vector2 position = b2body.getPosition();


        BodyDef bdef = new BodyDef();
        bdef.position.set(20,20);

        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(2);



        fdef.shape = shape;
        b2body.createFixture(fdef);

        b2body.setLinearDamping(3f);

//        b2body.createFixture(fdef).setUserData(this);

        setBounds(20,20,8,8);
    }

    /*public TextureRegion getFrame(float dt) {

    }*/

    public void update(float dt) {
//        System.out.println((int)(b2body.getPosition().x - getWidth() / 2));
        setPosition(Math.round(b2body.getPosition().x - getWidth() / 2) , Math.round(b2body.getPosition().y - getHeight() / 2));
    }

   /* public void draw(Batch batch){
        super.draw(batch);

    }*/

}
