package com.libgdx.magnets;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.libgdx.magnets.entities.Magnet;
import com.libgdx.magnets.entities.Robot;

import java.util.ArrayList;
import java.util.List;

public class MagneticForceManager {

    private List<Magnet> magnetList;
    private List<Robot> robotList;

    public MagneticForceManager(List<Magnet> magnetList, List<Robot> robotList) {

        this.magnetList = magnetList;
        this.robotList = robotList;

    }

    public MagneticForceManager() {
        magnetList = new ArrayList<Magnet>();
        robotList = new ArrayList<Robot>();

    }

    public void updateMagnetState(float x, float y) {

        Rectangle magnetRect;
        System.out.println("update magnetic state");

        for (Magnet magnet: magnetList
             ) {

            magnetRect = magnet.getBoundingRectangle();
            System.out.println(magnetRect.toString());
            System.out.println(x + ", " + y);


            if(magnetRect.contains(x,y)) {
                magnet.cycleState();
            }

//            System.out.println(magnet.);

//            if(magnet.)

        }

    }

    public void addMagnet(Magnet magnet) {
        magnetList.add(magnet);
    }

    public void addRobot(Robot robot) {
        robotList.add(robot);
    }

    public void act() {
        for (Magnet magnet: magnetList) {

            for (Robot robot : robotList) {
//                System.out.println("applying magnetic force");

                double distance = Math.sqrt(Math.pow(robot.getX() - magnet.getX(), 2) + Math.pow((robot.getY() - magnet.getY()), 2));
                Vector2 repelVector = new Vector2(robot.getX() - magnet.getX(), robot.getY() - magnet.getY());
                Vector2 attractVector = new Vector2(magnet.getX() - robot.getX(), magnet.getY() - robot.getY());
                float scale = 12 * (float) (1 / Math.pow(distance, 1.75));

                // magnetic force act
                if (magnet.getState() == Magnet.State.REPEL)
                    robot.b2body.applyLinearImpulse(repelVector.scl(scale), robot.b2body.getWorldCenter(), true);
                if (magnet.getState() == Magnet.State.ATTRACT)
                    robot.b2body.applyLinearImpulse(attractVector.scl(scale), robot.b2body.getWorldCenter(), true);

//                robot.b2body.applyLinearImpulse(new Vector2(1, 0), robot.b2body.getWorldCenter(), true);

            }

        }


    }

}
