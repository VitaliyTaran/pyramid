import entity.Point;
import entity.Pyramid;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    private static final double DELTA = 0.01;
    private static final double SUM_SQUARE_TRIANGLES_IN_PYRAMID = 73.22455571737483d;
    private static final Pyramid TESTING_PYRAMID = new Pyramid(
            new Point(2, 6, 2),
            new Point(0, 0, 0),
            new Point(4, 0, 0),
            new Point(4, 0, 4)
    );


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
        Point point = calculator.calculatePointOnHeight(new Point(0, 0, 0), new Point(6, 6, 0), 5);
        System.out.println(point.toString());
    }
}