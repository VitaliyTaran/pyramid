package com.epam.geometry;

import com.epam.geometry.entity.Point;
import com.epam.geometry.entity.Pyramid;
import com.epam.geometry.entity.Vector;
import com.epam.geometry.exaptions.NotAEnumException;
import org.apache.log4j.Logger;

public class Calculator {
   private final static Logger logger = Logger.getLogger(Calculator.class);

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
        try {
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
                default:
                    throw new NotAEnumException("CoordinatePlane not a enum");
            }
        }catch (NotAEnumException e ){
            logger.warn(e);
        }

        return result;
    }

    public Point calculatePointOnHeight(Point firstPoint, Point secondPoint, double height) {
        Point sepSecondPoint = new Point(secondPoint.getX(), firstPoint.getY(), secondPoint.getZ());
        double distanceFirstAndSecond = calculateDistanceBetweenPoints(firstPoint, secondPoint);
        double distanceFirstAndSepSecond = Math.sqrt(Math.pow(firstPoint.getX() - sepSecondPoint.getX(), 2) + Math.pow(firstPoint.getZ() - sepSecondPoint.getZ(), 2));
        double hY;
        if (firstPoint.getY() <= secondPoint.getY()) {
            hY = height - firstPoint.getY();
        } else {
            hY = height - secondPoint.getY();
        }
        double distanceSecondSepSecond = Math.sqrt(Math.pow(distanceFirstAndSecond, 2) - Math.pow(distanceFirstAndSepSecond, 2));
        double hX = hY * distanceFirstAndSepSecond / distanceSecondSepSecond;
        double dZ = Math.abs(firstPoint.getZ() - sepSecondPoint.getZ());
        double betta = Math.acos(dZ / distanceFirstAndSepSecond);
        double projectionX = hX * Math.sin(betta);
        double projectionZ = hX * Math.cos(betta);
        double xMax = Math.max(firstPoint.getX(), secondPoint.getX());
        double zMax = Math.max(firstPoint.getZ(), secondPoint.getZ());
        if (xMax > (firstPoint.getX() + projectionX)) {
            sepSecondPoint.setX(firstPoint.getX() + projectionX);
        } else {
            sepSecondPoint.setX(firstPoint.getX() - projectionX);
        }
        sepSecondPoint.setY(height);
        if (zMax > (firstPoint.getZ() + projectionZ)) {
            sepSecondPoint.setZ(firstPoint.getZ() + projectionZ);
        } else {
            sepSecondPoint.setZ(firstPoint.getZ() - projectionZ);
        }
        return sepSecondPoint;
    }

    public double planeSectionOfPyramid(Pyramid pyramid, double height) {

        Point firstPointOnHeight = calculatePointOnHeight(pyramid.getHeadPoint(), pyramid.getFirstBasePoint(), height);
        Point secondPointOnHeight = calculatePointOnHeight(pyramid.getHeadPoint(), pyramid.getSecondBasePoint(), height);
        Point thirdPointOnHeight = calculatePointOnHeight(pyramid.getHeadPoint(), pyramid.getThirdBasePoint(), height);
        Pyramid miniPyramid = new Pyramid(pyramid.getHeadPoint(), firstPointOnHeight, secondPointOnHeight, thirdPointOnHeight);
        double spacePyramid = calculateSpacePyramid(pyramid);
        double spaceMiniPyramid = calculateSpacePyramid(miniPyramid);
        return spacePyramid / spaceMiniPyramid;
    }
}