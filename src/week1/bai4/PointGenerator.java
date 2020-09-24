package week1.bai4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PointGenerator {
    public static void main(String[] args) {
        Point A = new Point(800, 800, 0); //8000 points distance to A not over 400 units
        Point B = new Point(4000, 800, 0); //10000 points distance to B not over 500 units
        Point C = new Point(2400, 2400, 0); //12000 points distance to C not over 600 units
        List<Point> pointList = new ArrayList<>();
        pointList.addAll(generatePoint(8000, A, 400));
        pointList.addAll(generatePoint(10000, B, 500));
        pointList.addAll(generatePoint(12000, C, 600));
        System.out.println(pointList.size());
        pointList.forEach(point -> {
            if (point.getDistanceToX() >= 599)
                System.out.println(point);
        });
    }

    public static boolean check(List<Point> pointList, int numberPoint) {
        return pointList.size() >= numberPoint;
    }

    public static List<Point> generatePoint(int numberPoint, Point X, int maxDistance) {
        List<Point> pointList = new ArrayList<>();
        Random random = new Random();
        while (true) {
            if (check(pointList, numberPoint))
                break;
            int x = random.nextInt(2 * maxDistance) + X.getX() - maxDistance;
            int y = random.nextInt(2 * maxDistance) + X.getY() - maxDistance;
            double distance = Math.sqrt(Math.pow(x - X.getX(), 2) + Math.pow(y - X.getY(), 2));
            if (distance <= maxDistance)
                pointList.add(new Point(x, y, distance));
        }
        return pointList;
    }
}
