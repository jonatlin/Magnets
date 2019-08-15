package com.libgdx.magnets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.libgdx.magnets.Stages.HudStage;
import com.libgdx.magnets.entities.Magnet;
import com.libgdx.magnets.entities.Power;
import com.libgdx.magnets.entities.Robot;
import com.libgdx.magnets.entities.WallBody;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameManager {

    private List<Magnet> magnetList;
    private List<Robot> robotList;
    private List<Power> powerList;

    private HudStage hudStage;
    private World world;

    private int score = 0;

    public GameManager(List<Magnet> magnetList, List<Robot> robotList, List<Power> powerList) {

        this.magnetList = magnetList;
        this.robotList = robotList;
        this.powerList= powerList;

    }

    public GameManager() {
        magnetList = new ArrayList<Magnet>();
        robotList = new ArrayList<Robot>();
        powerList = new ArrayList<Power>();

    }

    public void draw(Batch batch) {
        for (Magnet magnet: magnetList) {
            magnet.draw(batch);
        }
        for (Robot robot: robotList) {
            robot.draw(batch);
        }
        for (Power power: powerList) {
            power.draw(batch);
        }
    }

    public void updateMagnetState(float x, float y) {

        Rectangle magnetRect;
        System.out.println("update magnetic state");

        for (Magnet magnet: magnetList) {

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

    public void generateWalls() {

        int leftX = 1;
        int rightX = Constants.GAME_WIDTH - 1;
        int downY = 1;

        // account for HUD
        int upY = Constants.GAME_HEIGHT - 9;

        // map boundaries
        WallBody.createWall(world, leftX, upY,rightX,upY);
        WallBody.createWall(world, rightX,downY,rightX,upY);
        WallBody.createWall(world, leftX,downY,rightX,downY);
        WallBody.createWall(world, leftX,downY,leftX,upY);
    }

    public void generateNewLevel() {

        this.resetEntities();

        int minDistance = 6;
        int numMagnets = 4;
        int numPowers = 2;
        int numRobots = 1;

        ArrayList<Point> pointList = new ArrayList<Point>();

        // generate list of points at least minDistance apart.
        while(pointList.size() < (numMagnets + numPowers + numRobots)) {


            Point newPoint = new Point(MathUtils.random(3, Constants.GAME_WIDTH-4),MathUtils.random(3, 54));

            if(isMinDistanceFromPoints(newPoint, pointList, minDistance)) {
                pointList.add(newPoint);
            }


        }

        for(Point point : pointList) {
            if(numMagnets>0) {
                addMagnet(new Magnet(world, point.x, point.y));
                numMagnets--;
            }
            else if(numPowers>0) {
                addPower(new Power(point.x, point.y));
                numPowers--;
            }
            else if(numRobots>0) {
                addRobot(new Robot(world, point.x, point.y));
                numRobots--;
            }


        }

    }

    public boolean isMinDistanceFromPoints(Point newPoint, ArrayList<Point> pointList, int minDistance) {

        for(Point point: pointList) {
            if(newPoint.distance(point) < minDistance)
                return false;

        }

        return true;
    }

    public double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public void resetEntities() {
        for(Magnet magnet: magnetList)
            world.destroyBody(magnet.getBody());

        for(Robot robot: robotList)
            world.destroyBody(robot.getBody());

        magnetList.clear();
        robotList.clear();
        powerList.clear();

    }

    public void addMagnet(Magnet magnet) {
        magnetList.add(magnet);
    }

    public void addRobot(Robot robot) {
        robotList.add(robot);
    }

    public void addPower(Power power) {
        powerList.add(power);
    }

    public void setHudStage(HudStage hudStage) {
        this.hudStage = hudStage;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void updateHudScore() {
        this.hudStage.setScore(score);
    }

    public void act(float delta) {

            // Check if any robot hit a power piece and add magnetic forces to robot
            for (Robot robot : robotList) {

                robot.update(delta);

                updatePowerHit(robot);

                // Add forces from each magnet
                for (Magnet magnet: magnetList) {
//                System.out.println("applying magnetic force");
                    applyMagneticForce(magnet, robot);

            }

        }

            if(powerList.isEmpty()) {
                this.generateNewLevel();
            }

    }

    private void applyMagneticForce(Magnet magnet, Robot robot) {

        double distance = Math.sqrt(Math.pow(robot.getX() - magnet.getX(), 2) + Math.pow((robot.getY() - magnet.getY()), 2));
        Vector2 repelVector = new Vector2(robot.getX() - magnet.getX(), robot.getY() - magnet.getY());
        Vector2 attractVector = new Vector2(magnet.getX() - robot.getX(), magnet.getY() - robot.getY());
        float scale = 12 * (float) (1 / Math.pow(distance, 1.75));

        // magnetic force act
        if (magnet.getState() == Magnet.State.REPEL)
            robot.getBody().applyLinearImpulse(repelVector.scl(scale), robot.getBody().getWorldCenter(), true);
        if (magnet.getState() == Magnet.State.ATTRACT)
            robot.getBody().applyLinearImpulse(attractVector.scl(scale), robot.getBody().getWorldCenter(), true);

    }

    private void updatePowerHit(Robot robot) {

        final Rectangle robotRect;

            robotRect = robot.getBoundingRectangle();

        for (Iterator<Power> iter = powerList.listIterator(); iter.hasNext(); ) {
            Power power = iter.next();
            if (power.getBoundingRectangle().overlaps(robotRect)) {
                iter.remove();
                score++;
                updateHudScore();
            }
        }



        }

        // for playtesting
        public Robot getRandomRobot() {
            if(!robotList.isEmpty())
                return robotList.get(0);
            else
                return null;
        }

        public void dispose() {
            world.dispose();
        }



}
