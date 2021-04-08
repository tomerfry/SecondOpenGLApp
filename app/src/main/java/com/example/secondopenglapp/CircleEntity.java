package com.example.secondopenglapp;

public class CircleEntity {

    private CircleModel circleModel;
    private float posX, posY, posZ;
    private float radius;

    public CircleEntity(CircleModel circleModel, float posX, float posY, float posZ, float radius) {
        this.circleModel = circleModel;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.radius = radius;
    }

    public CircleModel getCircleModel() {
        return circleModel;
    }

    public void setCircleModel(CircleModel circleModel) {
        this.circleModel = circleModel;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getPosZ() {
        return posZ;
    }

    public void setPosZ(float posZ) {
        this.posZ = posZ;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
