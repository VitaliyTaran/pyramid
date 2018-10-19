import entity.Point;
import entity.Pyramid;

public class Calculator {
    public double getDistanceBetweenPoints(Point firstPoint, Point secondPoint) {
        return Math.sqrt(Math.pow((firstPoint.getX() - secondPoint.getX()), 2)
                + Math.pow((firstPoint.getY() - secondPoint.getY()), 2)
                + Math.pow((firstPoint.getZ() - secondPoint.getZ()), 2));
    }

    public double getSpacePyramid(Pyramid pyramid) {
        Point vectorHeadFirstBasePoint = new Point(
                pyramid.getHeadPoint().getX() - pyramid.getFirstBasePoint().getX(),
                pyramid.getHeadPoint().getY() - pyramid.getFirstBasePoint().getY(),
                pyramid.getHeadPoint().getZ() - pyramid.getFirstBasePoint().getZ());
        Point vectorHeadSecondBasePoint = new Point(
                pyramid.getHeadPoint().getX() - pyramid.getSecondBasePoint().getX(),
                pyramid.getHeadPoint().getY() - pyramid.getSecondBasePoint().getY(),
                pyramid.getHeadPoint().getZ() - pyramid.getSecondBasePoint().getZ());
        Point vectorHeadThirdHeadPoint = new Point(
                pyramid.getHeadPoint().getX() - pyramid.getThirdBasePoint().getX(),
                pyramid.getHeadPoint().getY() - pyramid.getThirdBasePoint().getY(),
                pyramid.getHeadPoint().getZ() - pyramid.getThirdBasePoint().getZ());

        return (vectorHeadFirstBasePoint.getX() * vectorHeadSecondBasePoint.getY() * vectorHeadThirdHeadPoint.getZ()
                + vectorHeadThirdHeadPoint.getX() * vectorHeadFirstBasePoint.getY() * vectorHeadSecondBasePoint.getZ()
                + vectorHeadFirstBasePoint.getZ() * vectorHeadSecondBasePoint.getX() * vectorHeadThirdHeadPoint.getY()
                - vectorHeadThirdHeadPoint.getX() * vectorHeadSecondBasePoint.getY() * vectorHeadFirstBasePoint.getZ()
                - vectorHeadFirstBasePoint.getX() * vectorHeadThirdHeadPoint.getY() * vectorHeadSecondBasePoint.getZ()
                - vectorHeadSecondBasePoint.getX() * vectorHeadFirstBasePoint.getY() * vectorHeadThirdHeadPoint.getZ()) / 6;
    }

    private double getTriangleSquare(Point first, Point second, Point third) {
        double distanceBetweenFirstSecond = getDistanceBetweenPoints(first, second);
        double distanceBetweenFirstThird = getDistanceBetweenPoints(first, third);
        double distanceBetweenSecondThird = getDistanceBetweenPoints(second, third);
        double semiperimeter = (distanceBetweenFirstSecond + distanceBetweenFirstThird + distanceBetweenSecondThird) / 2;
        return Math.sqrt(semiperimeter
                * (semiperimeter - distanceBetweenFirstSecond)
                * (semiperimeter - distanceBetweenFirstThird)
                * semiperimeter - distanceBetweenSecondThird);
    }

    public double getSummSquareTriangles(Pyramid pyramid) {

        return getTriangleSquare(pyramid.getHeadPoint(), pyramid.getFirstBasePoint(), pyramid.getSecondBasePoint())
                + getTriangleSquare(pyramid.getHeadPoint(), pyramid.getFirstBasePoint(), pyramid.getThirdBasePoint())
                + getTriangleSquare(pyramid.getHeadPoint(), pyramid.getSecondBasePoint(), pyramid.getThirdBasePoint())
                + getTriangleSquare(pyramid.getFirstBasePoint(), pyramid.getSecondBasePoint(), pyramid.getThirdBasePoint());
    }
}