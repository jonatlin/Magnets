package com.libgdx.magnets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.libgdx.magnets.Stages.HudStage;
import com.libgdx.magnets.entities.Magnet;
import com.libgdx.magnets.entities.Power;
import com.libgdx.magnets.entities.Robot;
import com.libgdx.magnets.entities.WallBody;
import com.libgdx.magnets.utility.Constants;
import com.libgdx.magnets.utility.Point;

//import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameManager {

    // entities
    private List<Magnet> magnetList;
    private List<Robot> robotList;
    private List<Power> powerList;

    // background
    private Sprite background;

    // hud
    private HudStage hudStage;

    private World world;

    private int score = 0;

    private Constants.GameMode mode;
    private float timeCount;

    private int countdown = 120;

    public GameManager() {
        magnetList = new ArrayList<Magnet>();
        robotList = new ArrayList<Robot>();
        powerList = new ArrayList<Power>();

        background = new Sprite((new Texture(Gdx.files.internal("backgrounds/play_background3.png"))));
        background.setPosition(0, 0);
    }

    public void draw(Batch batch) {

        background.draw(batch);

        for (Robot robot : robotList) {
            robot.draw(batch);
        }
        for (Power power : powerList) {
            power.draw(batch);
        }
        for (Magnet magnet : magnetList) {
            magnet.draw(batch);
        }


    }

    public void checkClick(float x, float y) {

        updateMagnetClick(x, y);
//        checkExitClick(x,y);

    }

//    public void checkExitClick(float x,float y) {
//
//        if (hudStage.hit(x,y, true) == hudStage.getExitButton()) {
//
//        }
//
//    }

    // if a magnet is clicked, change its state
    public void updateMagnetClick(float x, float y) {

        Rectangle magnetRect;
//        System.out.println("update magnetic state: " + x + ", " + y);

        for (Magnet magnet : magnetList) {

            magnetRect = magnet.getBoundingRectangle();
           /* System.out.println(magnetRect.toString());
            System.out.println(x + ", " + y);*/


            if (magnetRect.contains(x, y)) {
                magnet.cycleState();
            }

        }

    }

    // add walls around play area
    public void generateWalls() {

        int leftX = 1;
        int rightX = Constants.GAME_WIDTH - 1;
        int downY = 1;

        // account for HUD
        int upY = Constants.GAME_HEIGHT - 8;

        // map boundaries
        WallBody.createWall(world, leftX, upY, rightX, upY);
        WallBody.createWall(world, rightX, downY, rightX, upY);
        WallBody.createWall(world, leftX, downY, rightX, downY);
        WallBody.createWall(world, leftX, downY, leftX, upY);
    }

    // create randomized level
    public void generateNewLevel() {

        this.resetEntities();

        double minDistance = 7;

        int numMagnets = MathUtils.random(3,4);
        int numPowers = MathUtils.random(2,4);
//        int numRobots = MathUtils.random(1,2);
        int numRobots = 1;

        // magnet width/height
        int magnetSize = 4;
        int robotSize = 5;
        int powerSize = 5;

        ArrayList<Point> pointList = new ArrayList<Point>();

        // generate list of points at least minDistance apart.
        while (pointList.size() < (numMagnets + numPowers + numRobots)) {

            // if entities spawn touching boxes can overlap
            Point newPoint = new Point(MathUtils.random(2, Constants.GAME_WIDTH - 4), MathUtils.random(3, 50));

            if (isMinDistanceFromPoints(newPoint, pointList, minDistance)) {
                pointList.add(newPoint);
            }
        }

        for (Point point : pointList) {
            if (numMagnets > 0) {
                addMagnet(new Magnet(world, (int)point.x, (int)point.y));
                numMagnets--;
            } else if (numPowers > 0) {
                addPower(new Power((int)(point.x - powerSize / 2), (int)(point.y - powerSize / 2)));
                numPowers--;
            } else if (numRobots > 0) {
                addRobot(new Robot(world, (int)point.x, (int)point.y));
                numRobots--;
            }


        }

    }

    public boolean isMinDistanceFromPoints(Point newPoint, ArrayList<Point> pointList, double minDistance) {

        if (newPoint.x < 3 || newPoint.x > Constants.GAME_WIDTH - 6 || newPoint.y < 3 || newPoint.y > (Constants.GAME_HEIGHT - 8 - 6)) {
            return false;
        }

        for (Point point : pointList) {
//            if (Math.abs(newPoint.x - point.x) < 6 || Math.abs(newPoint.y - point.y) < 6)
            if (newPoint.distance(point) < minDistance)
                return false;
        }

       /* for(Point point: pointList) {
            if(newPoint.distance(point) < minDistance)
                return false;

        }*/

        System.out.println(newPoint.toString());

        return true;
    }

    // erase board
    private void resetEntities() {
        for (Magnet magnet : magnetList)
            world.destroyBody(magnet.getBody());

        for (Robot robot : robotList)
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

    public int getScore() {
        return score;
    }

    public void setGameMode(Constants.GameMode mode) {
        this.mode = mode;
    }

    public Constants.GameMode getGameMode() {
        return this.mode;
    }

    public void act(float delta) {
        if(mode != Constants.GameMode.FREE_PLAY) {
            updateCountdown(delta);
            updateHudTime();
        }

        // Check if any robot hit a power piece and add magnetic forces to robot
        for (Robot robot : robotList) {

            robot.update(delta);

            updatePowerHit(robot);

            // Add forces from each magnet
            for (Magnet magnet : magnetList) {
//                System.out.println("applying magnetic force");
                applyMagneticForce(magnet, robot);
            }
        }

        if (powerList.isEmpty()) {
            this.generateNewLevel();
        }

        if (hudStage.isCountdownDone()) {

        }
    }

    public boolean isCountdownDone() {
        return countdown<=0;
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

    // change countdown each second that has passed
    private void updateCountdown(float delta) {
        timeCount += delta;
        if (timeCount >= 1) {
            if (countdown > 0) {
                countdown--;
            }
            timeCount = 0;
        }
    }

    // change hud to reflect countdown status
    private void updateHudTime() {
        if (this.mode != Constants.GameMode.FREE_PLAY)
            hudStage.setTime(countdown);
    }

    // if robot hit a power, remove it and increment the score
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
        if (!robotList.isEmpty())
            return robotList.get(0);
        else
            return null;
    }

//    public void dispose() {
//        world.dispose();
//    }

}
