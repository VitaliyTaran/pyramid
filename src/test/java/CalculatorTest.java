import entity.Point;
import entity.Pyramid;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    private static final double DELTA = 0.01;

    @Test
    public void getDistanceBetweenPointsTest() {
        Point firstPoint = new Point(3, 4, 0);
        Point secondPoint = new Point(0, 0, 0);
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.getDistanceBetweenPoints(firstPoint, secondPoint), 5, DELTA);
    }

    @Test
    public void getSpacePyramideTest() {
        Pyramid pyramid = new Pyramid(
                new Point(2, 2, 6),
                new Point(0, 0, 0),
                new Point(4, 0, 0),
                new Point(4, 4, 0)
        );
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.getSpacePyramid(pyramid), 16, DELTA);
    }

    @Test
    public void getSummSquareTrianglesTest() {
        Pyramid pyramid = new Pyramid(
                new Point(0, 2, 0),
                new Point(0, 0, 2),
                new Point(0, 0, 0),
                new Point(2, 0, 0)
        );
        Calculator calculator = new Calculator();
        System.out.println(calculator.getSummSquareTriangles(pyramid));
    }
}