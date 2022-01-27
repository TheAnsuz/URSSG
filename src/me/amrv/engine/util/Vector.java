package me.amrv.engine.util;

public class Vector {
    private float x;
    private float y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public Vector add(Vector vector) {
        return new Vector(x + vector.x, y + vector.y);
    }

    public Vector subtract(Vector vector) {
        return new Vector(x - vector.x, y - vector.y);
    }

    public Vector scale(float scale) {
        return new Vector(x * scale, y * scale);
    }

    public static float dot(Vector a, Vector b) {
        return (a.x * b.x) + (a.y * b.y);
    }

    public static float cross(Vector a, Vector b) {
        return (a.x * b.y) - (a.y * b.x);
    }

    public float lengthSquared() {
        return (x * x) + (y * y);
    }

    public float length() {
        return (float) Math.sqrt(lengthSquared());
    }

    public Vector normal() {
        return this.scale(length());
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public static void main(String[] args) {
        Vector a = new Vector(2, 3).add(new Vector(5, 6)).subtract(new Vector(2, 3));
        System.err.println(a);
    }

    @Override
    public String toString() {
        return "Vector: (" + x + ", " + y + ")";
    }
}
