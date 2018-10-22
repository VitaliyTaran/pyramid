package entity;

public class Pyramid {
    private Point headPoint;
    private Point firstBasePoint;
    private Point secondBasePoint;
    private Point thirdBasePoint;

    public Pyramid(Point headPoint, Point firstBasePoint, Point secondBasePoint, Point thirdBasePoint) {
        this.headPoint = headPoint;
        this.firstBasePoint = firstBasePoint;
        this.secondBasePoint = secondBasePoint;
        this.thirdBasePoint = thirdBasePoint;
    }

    public Point getHeadPoint() {
        return headPoint;
    }

    public Point getFirstBasePoint() {
        return firstBasePoint;
    }

    public Point getSecondBasePoint() {
        return secondBasePoint;
    }

    public Point getThirdBasePoint() {
        return thirdBasePoint;
    }

}