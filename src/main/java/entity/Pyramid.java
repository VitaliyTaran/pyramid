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

    public void setHeadPoint(Point headPoint) {
        this.headPoint = headPoint;
    }

    public Point getFirstBasePoint() {
        return firstBasePoint;
    }

    public void setFirstBasePoint(Point firstBasePoint) {
        this.firstBasePoint = firstBasePoint;
    }

    public Point getSecondBasePoint() {
        return secondBasePoint;
    }

    public void setSecondBasePoint(Point secondBasePoint) {
        this.secondBasePoint = secondBasePoint;
    }

    public Point getThirdBasePoint() {
        return thirdBasePoint;
    }

    public void setThirdBasePoint(Point thirdBasePoint) {
        this.thirdBasePoint = thirdBasePoint;
    }
}
