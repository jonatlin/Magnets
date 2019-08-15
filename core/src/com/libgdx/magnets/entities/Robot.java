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
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Robot extends Sprite {

    public int characterWidth = 5;
    public int characterHeight = 5;

    public Body body;
    public World world;

    public Robot(World world, int pos_x, int pos_y) {

        super((new Texture(Gdx.files.internal("robot/robot_blue.png"))));
        // for set texture manually
//        setRegion(new Texture(Gdx.files.internal("MagnetMan.png")));

        this.world = world;

        BodyDef bdef = new BodyDef();
        bdef.position.set(pos_x, pos_y);

        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

//        CircleShape shape = new CircleShape();
//        shape.setRadius(2);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(characterWidth/2f, characterHeight/2f);

        fdef.shape = bodyShape;
        body.createFixture(fdef);
        body.setLinearDamping(5f);



//        body.createFixture(fdef).setUserData(this);

        setBounds(Math.round(body.getPosition().x - getWidth() / 2),Math.round(body.getPosition().y - getHeight() / 2),characterWidth,characterHeight);
    }

    public Body getBody() {
        return body;
    }

    public void update(float dt) {
//        System.out.println("Sprite position: " + getX() + ", " + getY());
//        System.out.println("box2d body position" + body.getPosition().x + ", " + body.getPosition().y );
//        System.out.println(Math.round(body.getPosition().x - getWidth() / 2) + ", " + Math.round(body.getPosition().y - getHeight() / 2));
        setPosition(Math.round(body.getPosition().x - getWidth() / 2) , Math.round(body.getPosition().y - getHeight() / 2));
    }


   /* public void draw(Batch batch){
        super.draw(batch);

    }*/

}
