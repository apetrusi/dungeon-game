package com.example.myapplication.Model;

public interface EntityMovement {
    abstract double moveLeft(double positionX);
    abstract double moveRight(double positionX);
    abstract double moveUp(double positionY);
    abstract double moveDown(double positionY);
}
