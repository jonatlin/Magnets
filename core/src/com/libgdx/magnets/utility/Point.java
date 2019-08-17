package com.libgdx.magnets.utility;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }



    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }


    public double distance(Point that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

}