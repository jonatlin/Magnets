package com.libgdx.magnets.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.libgdx.magnets.MagnetsGame;

public class MagnetMan extends GameObject {

    public MagnetMan() {


    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.circle(20,20,2);
        sr.end();
    }

}
