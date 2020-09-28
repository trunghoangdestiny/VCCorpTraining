package week1.bai4;

import java.util.*;
//sinh 30000 diem phan biet, dung set

public class PointGenerator {
    public static void main(String[] args) {
        Point A = new Point(800, 800, 0); //8000 points distance to A not over 400 units
        Point B = new Point(4000, 800, 0); //10000 points distance to B not over 500 units
        Point C = new Point(2400, 2400, 0); //12000 points distance to C not over 600 units
        Set<Point> pointSet = new HashSet<>();
        pointSet.addAll(generatePoint(8000, A, 400));
        pointSet.addAll(generatePoint(10000, B, 500));
        pointSet.addAll(generatePoint(12000, C, 600));
        System.out.println(pointSet.size());
        System.out.println(pointSet);
    }

    public static boolean check(Set<Point> pointSet, int numberPoint) {
        return pointSet.size() >= numberPoint;
    }

    public static Set<Point> generatePoint(int numberPoint, Point X, int maxDistance) {
        Set<Point> pointSet = new HashSet<>();
        Random random = new Random();
        while (true) {
            if (check(pointSet, numberPoint))
                break;
            int x = random.nextInt(2 * maxDistance) + X.getX() - maxDistance;
            int y = random.nextInt(2 * maxDistance) + X.getY() - maxDistance;
            double distance = Math.sqrt(Math.pow(x - X.getX(), 2) + Math.pow(y - X.getY(), 2));
            if (distance <= maxDistance)
                pointSet.add(new Point(x, y, distance));
        }
        return pointSet;
    }
}
