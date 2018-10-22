import entity.Point;
import entity.Pyramid;
import entity.Vector;

public class Calculator {
    public double calculateDistanceBetweenPoints(Point firstPoint, Point secondPoint) {
        return Math.sqrt(Math.pow((firstPoint.getX() - secondPoint.getX()), 2)
                + Math.pow((firstPoint.getY() - secondPoint.getY()), 2)
                + Math.pow((firstPoint.getZ() - secondPoint.getZ()), 2));
    }

    private Vector calculateVector(Point firstPoint, Point secondPoint) {
        return new Vector(
                firstPoint.getX() - secondPoint.getX(),
                firstPoint.getY() - secondPoint.getY(),
                firstPoint.getZ() - secondPoint.getZ());
    }

    public double calculateSpacePyramid(Pyramid pyramid) {
        Vector vectorHeadFirstBasePoint = calculateVector(pyramid.getHeadPoint(), pyramid.getFirstBasePoint());
        Vector vectorHeadSecondBasePoint = calculateVector(pyramid.getHeadPoint(), pyramid.getSecondBasePoint());
        Vector vectorHeadThirdHeadPoint = calculateVector(pyramid.getHeadPoint(), pyramid.getThirdBasePoint());

        return Math.abs((vectorHeadFirstBasePoint.getX() * vectorHeadSecondBasePoint.getY() * vectorHeadThirdHeadPoint.getZ()
                + vectorHeadThirdHeadPoint.getX() * vectorHeadFirstBasePoint.getY() * vectorHeadSecondBasePoint.getZ()
                + vectorHeadFirstBasePoint.getZ() * vectorHeadSecondBasePoint.getX() * vectorHeadThirdHeadPoint.getY()
                - vectorHeadThirdHeadPoint.getX() * vectorHeadSecondBasePoint.getY() * vectorHeadFirstBasePoint.getZ()
                - vectorHeadFirstBasePoint.getX() * vectorHeadThirdHeadPoint.getY() * vectorHeadSecondBasePoint.getZ()
                - vectorHeadSecondBasePoint.getX() * vectorHeadFirstBasePoint.getY() * vectorHeadThirdHeadPoint.getZ())) / 6;
    }

    private double calculateTriangleSquare(Point first, Point second, Point third) {
        double distanceBetweenFirstSecond = calculateDistanceBetweenPoints(first, second);
        double distanceBetweenFirstThird = calculateDistanceBetweenPoints(first, third);
        double distanceBetweenSecondThird = calculateDistanceBetweenPoints(second, third);
        double semiperimeter = (distanceBetweenFirstSecond + distanceBetweenFirstThird + distanceBetweenSecondThird) / 2;
        return Math.sqrt(semiperimeter
                * (semiperimeter - distanceBetweenFirstSecond)
                * (semiperimeter - distanceBetweenFirstThird)
                * semiperimeter - distanceBetweenSecondThird);
    }

    public double calculateSumSquareTriangles(Pyramid pyramid) {

        return calculateTriangleSquare(pyramid.getHeadPoint(), pyramid.getFirstBasePoint(), pyramid.getSecondBasePoint())
                + calculateTriangleSquare(pyramid.getHeadPoint(), pyramid.getFirstBasePoint(), pyramid.getThirdBasePoint())
                + calculateTriangleSquare(pyramid.getHeadPoint(), pyramid.getSecondBasePoint(), pyramid.getThirdBasePoint())
                + calculateTriangleSquare(pyramid.getFirstBasePoint(), pyramid.getSecondBasePoint(), pyramid.getThirdBasePoint());
    }

    public boolean isPyramid(Pyramid pyramid) {
        return calculateSpacePyramid(pyramid) > 0;
    }

    public boolean isPyramidBasedOnCoordinatePlane(Pyramid pyramid, CoordinatePlane coordinatePlane) {
        Point points[] = {pyramid.getFirstBasePoint(),
                pyramid.getSecondBasePoint(),
                pyramid.getThirdBasePoint()};
        boolean result = true;
        switch (coordinatePlane) {
            case XOY:
                for (Point point : points) {
                    if (point.getZ() != 0) {
                        result = false;
                        break;
                    }
                }
                break;
            case XOZ:
                for (Point point : points) {
                    if (point.getY() != 0) {
                        result = false;
                        break;
                    }
                }
                break;
            case YOZ:
                for (Point point : points) {
                    if (point.getX() != 0) {
                        result = false;
                        break;
                    }
                }
                break;
        }
        return result;
    }

    private double calculatePointBetweenTwoPointWithLimit(Point firstPoint, Point secondPoint, double yLimiter) {
        double dY = Math.abs(firstPoint.getY() - secondPoint.getY());
        double dX = Math.abs(firstPoint.getX() - secondPoint.getX());
        Point result = new Point(0d, yLimiter, 0d);
        double hy = 0;
        if (secondPoint.getY() >= firstPoint.getY()) {
            hy = yLimiter - firstPoint.getY();
        } else {
            hy = yLimiter - secondPoint.getY();
        }
        if (firstPoint.getX() <= secondPoint.getX()) {
            result.setX(firstPoint.getX() + (dX * hy) / dY);
        } else {
            result.setX(secondPoint.getX() + (dX * hy) / dY);
        }
        return result.getX();
    }

    public Point calculatePoint(Point osnv, Point verx, double hsec) {
        double proectX = Math.abs(osnv.getX() - verx.getX());
        double proectZ = Math.abs(osnv.getZ() - verx.getZ());
        double dBx = (Math.sqrt(Math.pow(proectX, 2) + Math.pow(proectZ, 2)));
        double dBy = Math.sqrt(Math.pow(proectX, 2) + Math.pow(Math.abs(osnv.getY() - verx.getY()), 2) + Math.pow(proectZ, 2));
        double a1x = calculatePointBetweenTwoPointWithLimit(new Point(0, 0, 0), new Point(dBx, dBy, 0), hsec);
        double v = 0;
        double a = Math.sqrt(Math.pow(osnv.getX() - verx.getX(), 2) + Math.pow(osnv.getZ() - verx.getZ(), 2));
        if (a != 0) {
            v = proectZ / a;
        }
        double alpha = Math.asin(v);
        double deltA1x = a * Math.cos(alpha);
        double deltA1z = a * Math.sin(alpha);
        return new Point(osnv.getX() + deltA1x, hsec, osnv.getZ() + deltA1z);
    }

    public double cutPyramid(Pyramid pyramid, double hsec) {
        Pyramid miniPyramid = new Pyramid(pyramid.getHeadPoint(),
                calculatePoint(pyramid.getHeadPoint(), pyramid.getFirstBasePoint(), hsec),
                calculatePoint(pyramid.getHeadPoint(), pyramid.getSecondBasePoint(), hsec),
                calculatePoint(pyramid.getHeadPoint(), pyramid.getThirdBasePoint(), hsec));
        double spacePyramid = calculateSpacePyramid(pyramid);
        double spaceMiniPyramid = calculateSpacePyramid(miniPyramid);
        return spacePyramid / spaceMiniPyramid;
    }
}