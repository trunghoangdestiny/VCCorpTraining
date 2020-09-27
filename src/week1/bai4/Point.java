package week1.bai4;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Point {
    private int x;
    private int y;
    private double distanceToX;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y &&
                Double.compare(point.distanceToX, distanceToX) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, distanceToX);
    }

    public Point(int x, int y, double distanceToX) {
        this.x = x;
        this.y = y;
        this.distanceToX = distanceToX;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getDistanceToX() {
        return distanceToX;
    }

    public void setDistanceToX(double distanceToX) {
        this.distanceToX = distanceToX;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", distance=" + distanceToX +
                '}';
    }
}
