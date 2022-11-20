package com.example.json_parser.model.java;

import androidx.annotation.Nullable;

public class SmartPoint {
    private int x;
    private int y;

    public SmartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /* Reimplement the equal() method */
    @Override
    public boolean equals(@Nullable Object obj) {
        // Are the same?
        if (this == obj) {
            return true;
        }
        // Is obj a null reference?
        if (obj == null) {
            return false;
        }
        // Do they belong to the same class?
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        // Get the reference of obj in a SmartPoint variable
        SmartPoint point = (SmartPoint) obj;
        // Do they have the same x and y co-ordinates
        return (this.x == point.x && this.y == point.y);
    }

    @Override
    public int hashCode() {
        return (this.x + this.y);
    }
}
