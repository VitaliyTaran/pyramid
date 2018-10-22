import entity.Point;
import entity.Pyramid;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    private static final double DELTA = 0.01;
    private static final double SUM_SQUARE_TRIANGLES_IN_PYRAMID = 73.22455571737483d;
    private static final double RATIO_BETWEEN_TWO_FIGURES = 2.16172782113783808E1;
    private static final Pyramid TESTING_PYRAMID = new Pyramid(
            new Point(2, 6, 2),
            new Point(0, 0, 0),
            new Point(4, 0, 0),
            new Point(4, 0, 4)
    );

    @Test
    public void getDistanceBetweenPointsTest() {
        Calculator calculator = new Calculator();
        double distanceBetweenPoints = calculator.calculateDistanceBetweenPoints(TESTING_PYRAMID.getSecondBasePoint(), TESTING_PYRAMID.getThirdBasePoint());
        Assert.assertEquals(distanceBetweenPoints, 4, DELTA);
    }

    @Test
    public void getSpacePyramidTest() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculateSpacePyramid(TESTING_PYRAMID), 16, DELTA);
    }

    @Test
    public void getSumSquareTrianglesTest() {
        //To do
        //Given
        //When
        //Then

        Calculator calculator = new Calculator();
        double summSquareTriangles = calculator.calculateSumSquareTriangles(TESTING_PYRAMID);
        Assert.assertEquals(summSquareTriangles, SUM_SQUARE_TRIANGLES_IN_PYRAMID, DELTA);
    }

    @Test
    public void isPyramidTest() {
        Calculator calculator = new Calculator();
        boolean expected = calculator.isPyramid(TESTING_PYRAMID);
        Assert.assertEquals(expected, true);
    }

    @Test
    public void isPyramidBasedOnXOYTest() {
        Calculator calculator = new Calculator();
        CoordinatePlane xoy = CoordinatePlane.XOY;
        boolean isPyramidBasedOnCoordinatePlane = calculator.isPyramidBasedOnCoordinatePlane(TESTING_PYRAMID, xoy);
        Assert.assertEquals(isPyramidBasedOnCoordinatePlane, false);
    }

    @Test
    public void isPyramidBasedOnXOZTest() {
        Calculator calculator = new Calculator();
        CoordinatePlane xoz = CoordinatePlane.XOZ;
        boolean isPyramidBasedOnCoordinatePlane = calculator.isPyramidBasedOnCoordinatePlane(TESTING_PYRAMID, xoz);
        Assert.assertEquals(isPyramidBasedOnCoordinatePlane, true);
    }

    @Test
    public void isPyramidBasedOnYOZTest() {
        Calculator calculator = new Calculator();
        CoordinatePlane yoz = CoordinatePlane.YOZ;
        boolean isPyramidBasedOnCoordinatePlane = calculator.isPyramidBasedOnCoordinatePlane(TESTING_PYRAMID, yoz);
        Assert.assertEquals(isPyramidBasedOnCoordinatePlane, false);
    }

    @Test
    public void calculatePointTest() {
        Calculator calculator = new Calculator();
        Point point = calculator.calculatePoint(new Point(0, 0, 0), new Point(4,4,4), 2);
        System.out.println(point.toString());
    }

    @Test
    public void cutPyramidTest() {
        Calculator calculator = new Calculator();
        double space = calculator.cutPyramid(TESTING_PYRAMID, 2);
        Assert.assertEquals(space, RATIO_BETWEEN_TWO_FIGURES, DELTA);
    }
}