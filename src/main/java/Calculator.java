import entity.Point;
import entity.Pyramid;
import entity.Vector;

public class Calculator {
    private double calculateDistanceBetweenPoints(Point firstPoint, Point secondPoint) {
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

    public Point calculatePointOnHeight(Point firstPoint, Point secondPoint, double height) {
        Point sepSecondPoint = new Point(secondPoint.getX(), firstPoint.getY(), secondPoint.getZ());
        double distanceFirstAndSecond = calculateDistanceBetweenPoints(firstPoint, secondPoint);
        double distanceFirstAndSepSecond = Math.sqrt(Math.pow(firstPoint.getX() - sepSecondPoint.getX(), 2) + Math.pow(firstPoint.getZ() - sepSecondPoint.getZ(), 2));
        double hY = 0;
        if (firstPoint.getY() <= secondPoint.getY()) {
            hY = height - firstPoint.getY();
        } else {
            hY = height - secondPoint.getY();
        }
        double distanceSeconSepSecond = Math.sqrt(Math.pow(distanceFirstAndSecond, 2) - Math.pow(distanceFirstAndSepSecond, 2));
        double hX = hY * distanceFirstAndSepSecond / distanceSeconSepSecond;
        double dX = Math.abs(firstPoint.getX() - sepSecondPoint.getX());
        double dZ = Math.abs(firstPoint.getZ() - sepSecondPoint.getZ());
        double betta = Math.acos(dZ / distanceFirstAndSepSecond);
        double proectionX = hX * Math.sin(betta);
        double proectionZ = hX * Math.cos(betta);
        sepSecondPoint.setX(firstPoint.getX() + proectionX);
        sepSecondPoint.setY(height);
        sepSecondPoint.setZ(firstPoint.getZ() + proectionZ);
        return sepSecondPoint;
    }

}